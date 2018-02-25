package com.edusanchezcon.katas.sudoku;

import java.util.stream.IntStream;

public class SudokuBoard {

    private int[] cells;
    private GroupOfNine[] lines = new GroupOfNine[9];
    private GroupOfNine[] columns = new GroupOfNine[9];
    private GroupOfNine[] quadrants = new GroupOfNine[9];

    public SudokuBoard(int[] cells){
        this.cells = cells;

        IntStream.range(0, 9).forEach(
                i -> {
                    lines[i] = new GroupOfNine( collectLine(i));
                    columns[i] = new GroupOfNine( collectColumn(i));
                    quadrants[i] = new GroupOfNine( collectQuadrant(i));
                }
        );
    }

    public boolean isSolved(){

        for (int i=0; i<9; i++){
            if (!lines[i].isSolved()) return false;
            if (!columns[i].isSolved()) return false;
            if (!quadrants[i].isSolved()) return false;
        }

        return true;
    }

    private int[] collectLine(final Integer i) {
        return IntStream.range(0, 9)
                .map(j -> cells[i*9 + j])
                .toArray();
    }

    private int[] collectColumn(final Integer i) {
        return IntStream.range(0, 9)
                .map(j -> cells[j*9 + i])
                .toArray();
    }

    private int[] collectQuadrant(final Integer q) {
        int i = q / 3;
        int j = q % 3;
        return IntStream.range(0, 3)
                .flatMap(x -> IntStream.range(0, 3)
                .map(y -> cells[9*(3*i+x) + 3*j+y]))
                .toArray();
    }
}
