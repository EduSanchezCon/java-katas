package com.edusanchezcon.katas.sudoku.mother;

import com.edusanchezcon.katas.sudoku.SudokuBoard;

public class SudokuBoardMother {

    public  static SudokuBoard solvedBoard(){
        return new SudokuBoard( new int[]{
                9, 6, 7,   1, 8, 4,   2, 3, 5,
                1, 5, 3,   9, 2, 7,   6, 8, 4,
                2, 4, 8,   6, 3, 5,   1, 7, 9,

                6, 2, 9,   8, 4, 3,   7, 5, 1,
                7, 8, 4,   5, 1, 9,   3, 2, 6,
                5, 3, 1,   7, 6, 2,   9, 4, 8,

                8, 9, 2,   4, 7, 6,   5, 1, 3,
                3, 1, 5,   2, 9, 8,   4, 6, 7,
                4, 7, 6,   3, 5, 1,   8, 9, 2
        }
        );
    }

    public static SudokuBoard incompleteBoard() {
        return new SudokuBoard(new int[]{
                0, 0, 7,    0, 8, 0,    0, 0, 0,
                0, 0, 3,    9, 2, 0,    6, 0, 0,
                2, 4, 0,    6, 3, 5,    0, 0, 0,

                6, 2, 9,    0, 0, 0,    0, 0, 1,
                0, 8, 0,    5, 1, 9,    0, 2, 0,
                5, 0, 0,    0, 0, 0,    9, 4, 8,

                0, 0, 0,    4, 7, 6,    0, 1, 3,
                0, 0, 5,    0, 9, 8,    4, 0, 0,
                0, 0, 0,    0, 5, 0,    8, 0, 0
        }
        );
    }
}
