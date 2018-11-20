package com.edusanchezcon.katas.sudoku;

import org.apache.commons.collections4.SetUtils;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SudokuSolver {

    private SudokuBoard board;
    private final int recursion;

    private long cont;

    public SudokuSolver(SudokuBoard board){
        this(board, 0);
    }

    public SudokuSolver(SudokuBoard board, int recursion){
        this.board = board.clone();
        this.recursion = recursion;
    }

    public SudokuBoard solve(){
        int filledPositions = -1;
        while (filledPositions != 0) {
            //System.out.println("FilledPositions = " + filledPositions);
            filledPositions = fillWithIndividualCandidates();
            if (filledPositions == 0 && !board.isSolved()){
                filledPositions = fillWithSiblingsCandidates();
            }
            if (filledPositions == 0 && !board.isSolved() && recursion > 0){
                tryCombinations();
            }
        }
        return board;
    }

    private int fillWithIndividualCandidates() {
        int filledPositions = -1;
        while (filledPositions != 0){
            filledPositions = 0;
            for (int i=0; i<81; i++){
                if (board.getCellValue(i) !=0 ) continue;

                final SetUtils.SetView<Integer> candidates = getCandidates(i);
                if (candidates.size()==1){
                    final Integer value = candidates.stream().findFirst().get();
                    board.setCellValue(i, value);
                    filledPositions++;

                    //System.out.println("Filling "+ Util.printCoordinates(i) + " with " + value);
                }
            }
        }
        return filledPositions;
    }

    private int fillWithSiblingsCandidates() {
        int filledPositions = 0;

        for (int i=0; i<81; i++){
            if (board.getCellValue(i) !=0 ) continue;

            final SetUtils.SetView<Integer> individualCandidates = getCandidates(i);

            Set<Integer> siblingCandidates = getCandidatesInSiblingsInLine(i);
            SetUtils.SetView<Integer> newCandidates = SetUtils.difference(individualCandidates, siblingCandidates);
            String siblingType = "line";

            if (newCandidates.size() != 1) {
                siblingCandidates = getCandidatesInSiblingsInColumn(i);
                newCandidates = SetUtils.difference(individualCandidates, siblingCandidates);
                siblingType = "column";
            }

            if (newCandidates.size() != 1) {
                siblingCandidates = getCandidatesInSiblingsInQuadrant(i);
                newCandidates = SetUtils.difference(individualCandidates, siblingCandidates);
                siblingType = "quadrant";
            }

            if (newCandidates.size()==1){
                //System.out.println(siblingType +"Candidates ["+ siblingCandidates + "] ");
                //System.out.println("individualCandidates ["+ individualCandidates + "] ");
                final Integer value = newCandidates.stream().findFirst().get();
                board.setCellValue(i, value);
                filledPositions++;

                //System.out.println("_Filling " + i + " " + Util.printCoordinates(i) + " with " + value);
                break;
            }
        }
        return filledPositions;
    }

    private SetUtils.SetView<Integer> getCandidates(int index) {
        cont++;
        return SetUtils.intersection(
                SetUtils.intersection(
                        board.getLineAtIndex(index).get(),
                        board.getColumnAtIndex(index).get()),
                board.getQuadrantAtIndex(index).get());
    }

    private Set<Integer> getCandidatesInSiblingsInLine(int index){
        final int lineIndex = index / 9;
        final int columnIndex = index % 9;
        return IntStream.range(0, 9)
                .filter(x -> x != columnIndex)
                .map(x -> 9*lineIndex + x)
                .filter(n -> board.getCellValue(n) == 0)
                .boxed()
                .flatMap(i -> this.getCandidates(i).stream())
                .collect(Collectors.toSet());
    }

    private Set<Integer> getCandidatesInSiblingsInColumn(int index){
        final int lineIndex = index / 9;
        final int columnIndex = index % 9;
        return IntStream.range(0, 9)
                .filter(y -> y != lineIndex)
                .map(y -> y*9 + columnIndex)
                .filter(n -> board.getCellValue(n) == 0)
                .boxed()
                .flatMap(i -> this.getCandidates(i).stream())
                .collect(Collectors.toSet());
    }

    private Set<Integer> getCandidatesInSiblingsInQuadrant(final int index){
        final int lineIndex = index / 9;
        final int columnIndex = index % 9;
        int i = lineIndex / 3;
        int j = columnIndex / 3;
        return IntStream.range(0, 3)
                .flatMap(x -> IntStream.range(0, 3)
                        .map(y -> 9*(3*i+x) + 3*j+y))
                .filter(n -> n != index)
                .filter(n -> board.getCellValue(n) == 0)
                .boxed()
                .flatMap(n -> this.getCandidates(n).stream())
                .collect(Collectors.toSet());
    }

    private void tryCombinations(){
        Map<Integer, Set<Integer>> cellsWith2Candidates = getCellsWithCandidates();

        outer:
        for (Map.Entry<Integer, Set<Integer>> entry : cellsWith2Candidates.entrySet()) {
            final ConcurrentLinkedQueue<Integer> candidates = new ConcurrentLinkedQueue<>(entry.getValue());

            final Integer cellIndex = entry.getKey();
            for (Integer possibleValue : entry.getValue()) {

                //System.out.println("Trying to solve filling " + Util.printCoordinates(cellIndex) + " with " + possibleValue);
                final SudokuBoard tryBoard = board.clone();
                tryBoard.setCellValue(cellIndex, possibleValue);
                final SudokuSolver trySolver = new SudokuSolver(tryBoard, recursion - 1);
                final SudokuBoard solvedTryBoard = trySolver.solve();

                cont += trySolver.getCont();

                if (solvedTryBoard.isSolved()) {
                    board = solvedTryBoard;
                    break outer;
                }
            }
        }
    }

    private Map<Integer, Set<Integer>> getCellsWithCandidates() {
        return IntStream.range(0, 81).boxed()
            .filter(i -> board.getCellValue(i) == 0)
            .map(i -> new AbstractMap.SimpleEntry<Integer, Set<Integer>>(i, getCandidates(i)))
            .sorted(Comparator.comparing(e -> e.getValue().size()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


    public long getCont() {
        return cont;
    }
}
