package com.edusanchezcon.katas.sudoku;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SudokuBoard implements Cloneable{

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

    @Override
    public SudokuBoard clone() {
        return new SudokuBoard(Arrays.copyOf(this.cells, this.cells.length));
    }


    public GroupOfNine getLineAtIndex(int p) {
        return lines[p/9];
    }

    public GroupOfNine getColumnAtIndex(int p) {
        return columns[p%9];
    }

    public GroupOfNine getQuadrantAtIndex(int p) {
        return quadrants[3 * (p/(9*3)) + (p%9)/3];
    }

    public int getCellValue(int index) {
        return cells[index];
    }

    public void setCellValue(int index, Integer n){
        cells[index] = n;

        getLineAtIndex(index).get().remove(n);
        getColumnAtIndex(index).get().remove(n);
        getQuadrantAtIndex(index).get().remove(n);
    }

    public void printState(){
        IntStream.range(0,9).forEach(i -> System.out.println("Line-" +i+ lines[i]));
        IntStream.range(0,9).forEach(i -> System.out.println("Column-" +i+ columns[i]));
        IntStream.range(0,9).forEach(i -> System.out.println("Quadrant-" +i+ quadrants[i]));
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(200);
        for (int y=0; y<9; y++){
            for (int x=0; x<9; x++) {
                builder.append(cells[y * 9 + x]).append(" ");
                if (x%3==2) builder.append("  ");
            }
            builder.append("\n");
            if (y%3 == 2) builder.append("\n");
        }
        return builder.toString();
    }
}
