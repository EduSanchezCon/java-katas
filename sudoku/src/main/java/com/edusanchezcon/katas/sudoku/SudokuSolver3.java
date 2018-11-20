package com.edusanchezcon.katas.sudoku;

import org.apache.commons.collections4.SetUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SudokuSolver3 {

    private SudokuBoard board;
    private int recursion;
    private final boolean isRoot;

    private long cont;

    private Set<Integer>[] candidates = new Set[81];

    public SudokuSolver3(SudokuBoard board){
        this(board, 1, true);
    }

    public SudokuSolver3(SudokuBoard board, int recursion){
        this(board, recursion, false);
    }

    public SudokuSolver3(SudokuBoard board, int recursion, boolean isRoot){
        this.board = board.clone();
        this.recursion = recursion;
        this.isRoot = isRoot;
        init();
    }

    private void init(){
        IntStream.range(0, 81).forEach(i -> candidates[i] = discoverCandidates(i));
    }

    private Set<Integer> discoverCandidates(int index) {
        if (board.getCellValue(index)!=0) return  SetUtils.<Integer>emptySet();
        return SetUtils.intersection(
                SetUtils.intersection(
                        board.getLineAtIndex(index).get(),
                        board.getColumnAtIndex(index).get()),
                board.getQuadrantAtIndex(index).get())
                .toSet();
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
                tryCombinations(false);
            }
            if (isRoot){
                while (!board.isSolved()){
                    recursion++;
                    tryCombinations(true);
                }
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

                final Set<Integer> candidates = getCandidates(i);
                if (candidates.size()==1){
                    final Integer value = candidates.stream().findFirst().get();
                    setValue(i, value);
                    filledPositions++;

                    //System.out.println("Filling "+ Util.printCoordinates(i) + " with " + value);
                }
            }
        }
        return filledPositions;
    }

    private void setValue(int index, Integer value) {
        board.setCellValue(index, value);

        int line = index/9;
        int column = index%9;
        int quadrant = 3 * (index/(9*3)) + (index%9)/3;

        IntStream.range(0, 9)
                .peek(i -> candidates[line*9 + i].remove(value))
                .forEach(i -> candidates[i*9 + column].remove(value));

        int i = quadrant / 3;
        int j = quadrant % 3;
        IntStream.range(0, 3)
                .flatMap(x -> IntStream.range(0, 3)
                        .map(y -> 9*(3*i+x) + 3*j+y)
                ).forEach(elem -> candidates[elem].remove(value));
    }

    private int fillWithSiblingsCandidates() {
        int filledPositions = 0;

        for (int i=0; i<81; i++){
            if (board.getCellValue(i) !=0 ) continue;

            final Set<Integer> individualCandidates = getCandidates(i);

            Set<Integer> siblingCandidates = getCandidatesInSiblingsInLine(i);
            SetUtils.SetView<Integer> newCandidates = SetUtils.difference(individualCandidates, siblingCandidates);
            //String siblingType = "line";

            if (newCandidates.size() != 1) {
                siblingCandidates = getCandidatesInSiblingsInColumn(i);
                newCandidates = SetUtils.difference(individualCandidates, siblingCandidates);
                //siblingType = "column";
            }

            if (newCandidates.size() != 1) {
                siblingCandidates = getCandidatesInSiblingsInQuadrant(i);
                newCandidates = SetUtils.difference(individualCandidates, siblingCandidates);
                //siblingType = "quadrant";
            }

            if (newCandidates.size()==1){
                //System.out.println(siblingType +"Candidates ["+ siblingCandidates + "] ");
                //System.out.println("individualCandidates ["+ individualCandidates + "] ");
                final Integer value = newCandidates.stream().findFirst().get();
                setValue(i, value);
                filledPositions++;

                //System.out.println("_Filling " + i + " " + Util.printCoordinates(i) + " with " + value);
                break;
            }
        }
        return filledPositions;
    }

    private Set<Integer> getCandidates(int index) {
        //cont++;
        return candidates[index];
    }

    private Set<Integer> getCandidatesInSiblingsInLine(int index){
        final int lineIndex = index / 9;
        final int columnIndex = index % 9;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            if (i == columnIndex) continue;
            int x = 9*lineIndex + i;
            if (board.getCellValue(x) != 0) continue;
            set.addAll(getCandidates(x));
        }

        return set;
    }

    private Set<Integer> getCandidatesInSiblingsInColumn(int index){
        final int lineIndex = index / 9;
        final int columnIndex = index % 9;

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            if (i == lineIndex) continue;
            int x = i*9 + columnIndex;
            if (board.getCellValue(x) != 0) continue;
            set.addAll(getCandidates(x));
        }

        return set;
    }

    private Set<Integer> getCandidatesInSiblingsInQuadrant(final int index){
        final int lineIndex = index / 9;
        final int columnIndex = index % 9;
        int i = lineIndex / 3;
        int j = columnIndex / 3;


        Set<Integer> set = new HashSet<>();
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                int n =  9*(3*i+x) + 3*j+y;
                if (n == index) continue;
                if (board.getCellValue(n) != 0) continue;
                set.addAll(getCandidates(n));
            }
        }

        return set;
    }

    private void tryCombinations_old(){
        Map<Integer, Set<Integer>> cellsWith2Candidates = getCellsWithCandidates();

        board = cellsWith2Candidates.entrySet().stream().parallel()
                .flatMap(entry -> entry.getValue().stream()
                .map(possibleValue -> {
                    final SudokuBoard tryBoard = board.clone();
                    tryBoard.setCellValue(entry.getKey(), possibleValue);
                    final SudokuSolver3 trySolver = new SudokuSolver3(tryBoard, recursion - 1 );
                    return trySolver.solve();
                })).filter(SudokuBoard::isSolved).findAny().orElse(board);
    }

    private void tryCombinations(boolean parallel){
        Map<Integer, Set<Integer>> cellsWith2Candidates = getCellsWithCandidates();

        Stream<Map.Entry<Integer, Set<Integer>>> stream = new ArrayList<>(cellsWith2Candidates.entrySet()).stream();

        if (parallel) stream = stream.parallel();

        board = stream.map(entry -> {
                    final Integer cellIndex = entry.getKey();
                    for (Integer possibleValue : entry.getValue()) {

                        final SudokuBoard tryBoard = board.clone();
                        tryBoard.setCellValue(cellIndex, possibleValue);
                        final SudokuSolver2 trySolver = new SudokuSolver2(tryBoard, recursion - 1 );
                        final SudokuBoard solvedTryBoard = trySolver.solve();
                        if (solvedTryBoard.isSolved()) {
                            return solvedTryBoard;
                        }
                    }
                    return board;}).filter(SudokuBoard::isSolved).findAny().orElse(board);
    }

    private Map<Integer, Set<Integer>> getCellsWithCandidates() {
        return IntStream.range(0, 81).boxed()
            .filter(i -> board.getCellValue(i) == 0)
            .map(i -> new AbstractMap.SimpleEntry<Integer, Set<Integer>>(i, getCandidates(i)))
            //.filter(e -> e.getValue().size() <= recursion + 2)
            .sorted(Comparator.comparing(e -> e.getValue().size()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a,b) -> a,  () -> new LinkedHashMap<>()));
    }


    public long getCont() {
        return cont;
    }

    public int getRecursion(){ return  recursion;}
}
