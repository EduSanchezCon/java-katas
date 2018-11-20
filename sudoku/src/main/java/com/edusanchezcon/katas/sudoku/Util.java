package com.edusanchezcon.katas.sudoku;

public class Util {
    static String printCoordinates(int i) {
        return "[" + ((i / 9) + 1) + ", " + ((i % 9) + 1) + "]";
    }
}
