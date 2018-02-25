package com.edusanchezcon.katas.sudoku;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SudokuBoard {

    private int[] cells;

    public SudokuBoard(int[] cells){
        this.cells = cells;
    }

    public boolean isSolved(){

       for (int[] line : this.lines()){
            if (!isPartialCompleted(line)) return false;
        }

        for (int[] column : this.columns()){
            if (!isPartialCompleted(column)) return false;
        }

        for (int[] quadrant : this.quadrants()){
            if (!isPartialCompleted(quadrant)) return false;
        }

        return true;
    }

    public List<int[]> lines(){
        return IntStream.range(0, 9).boxed()
                .map(this::getLine)
                .collect(Collectors.toList());

    }

    private int[] getLine(final Integer i) {
        return IntStream.range(0, 9)
                .map(j -> cells[i*9 + j])
                .toArray();
    }

    public List<int[]> columns(){
        return IntStream.range(0, 9).boxed()
                .map(this::getColumn)
                .collect(Collectors.toList());

    }

    private int[] getColumn(final Integer i) {
        return IntStream.range(0, 9)
                .map(j -> cells[j*9 + i])
                .toArray();
    }

    public List<int[]> quadrants(){
        return IntStream.range(0, 9).boxed()
                .map(this::getQuadrant)
                .collect(Collectors.toList());

    }

    private int[] getQuadrant(final Integer q) {
        int i = q / 3;
        int j = q % 3;
        return IntStream.range(0, 3)
                .flatMap(x -> IntStream.range(0, 3)
                .map(y -> cells[9*(3*i+x) + 3*j+y]))
                .toArray();
    }

    public static boolean isPartialCompleted(int[] part){
        return Arrays.stream(part).sum() == 45
                && Arrays.stream(part).reduce(1, (a,b) -> a*b) == 362880;
    }

    /*
    public static boolean isPartialCompletedBit(int[] part){
        return Arrays.stream(part).filter(i -> i>0).map(i -> 1 << (i-1)).reduce((i1, i2) -> i1 | i2).getAsInt() == 511;
    }
    */
}
