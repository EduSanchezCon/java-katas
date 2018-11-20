package com.edusanchezcon.katas.sudoku;

public class PerfLauncherBacktracking {

    public static void main (String... args){

       // final SudokuBoard board = generateBoard1();

        PerfMetrics metrics = new PerfMetrics(5, 15);

        metrics.measure(() -> {
            int[][] board = generateBoardHardest();
            BacktrackingSudoku solver = new BacktrackingSudoku(board);

            solver.solve();
            //solver.printBoard();
        });

        System.out.println(metrics);
        //System.out.println(solvedBoard);
        //System.out.println(solver.getCont()); // 2.319.481
    }

    private static int[][] generateBoard1(){
        return new int[][]{
                {0, 0, 0,  6, 0, 2,  0, 0, 1},
                {0, 0, 7,  1, 0, 0,  9, 0, 0},
                {2, 0, 0,  0, 5, 4,  0, 0, 8},

                {3, 0, 9,  7, 1, 0,  0, 4, 0},
                {5, 0, 1,  0, 9, 0,  2, 0, 6},
                {0, 7, 0,  0, 4, 5,  1, 0, 3},

                {0, 0, 5,  0, 0, 7,  6, 0, 0},
                {9, 0, 0,  5, 0, 1,  0, 0, 0},
                {7, 0, 0,  8, 3, 0,  0, 0, 4}
        };
    }

    private static int[][] generateBoard2(){
        return new int[][]{
                {0, 4, 0,  3, 0, 0,  0, 0, 8},
                {7, 0, 0,  0, 6, 0,  0, 0, 0},
                {3, 9, 0,  0, 0, 0,  2, 4, 0},

                {0, 3, 0,  6, 5, 2,  0, 0, 0},
                {0, 0, 5,  0, 9, 0,  3, 0, 0},
                {0, 0, 0,  4, 7, 3,  0, 8, 0},

                {0, 5, 4,  0, 0, 0,  0, 3, 2},
                {0, 0, 0,  0, 4, 0,  0, 0, 1},
                {6, 0, 0,  0, 0, 9,  0, 7, 0}
        };
    }

    private static int[][] generateBoard3(){
        return new int[][]{
                {8, 3, 0,  0, 1, 9,  0, 0, 0},
                {0, 0, 5,  0, 0, 0,  0, 0, 0},
                {0, 0, 7,  2, 3, 0,  0, 1, 4},

                {0, 9, 2,  0, 0, 0,  0, 0, 0},
                {1, 0, 8,  0, 0, 0,  3, 0, 6},
                {0, 0, 0,  0, 0, 0,  9, 2, 0},

                {4, 8, 0,  0, 6, 1,  2, 0, 0},
                {0, 0, 0,  0, 0, 0,  7, 0, 0},
                {0, 0, 0,  8, 5, 0,  0, 6, 3}
        };
    }

    private static int[][] generateBoard4(){
        return new int[][]{
                {0, 0, 0,  0, 2, 0,  0, 9, 0},
                {0, 4, 0,  0, 0, 0,  5, 0, 8},
                {0, 0, 0,  9, 0, 0,  3, 1, 0},

                {9, 0, 0,  0, 0, 1,  7, 0, 0},
                {0, 0, 8,  0, 4, 0,  9, 0, 0},
                {0, 0, 2,  5, 0, 0,  0, 0, 1},

                {0, 6, 1,  0, 0, 3,  0, 0, 0},
                {5, 0, 4,  0, 0, 0,  0, 8, 0},
                {0, 8, 0,  0, 5, 0,  0, 0, 0}
        };
    }

    private static int[][] generateBoard5(){
        return new int[][]{
                {5, 0, 0,  6, 0, 9,  2, 0, 0},
                {0, 0, 0,  0, 0, 3,  8, 6, 4},
                {0, 0, 0,  0, 7, 0,  0, 0, 0},

                {0, 0, 0,  0, 0, 0,  0, 5, 1},
                {6, 0, 0,  3, 0, 2,  0, 0, 9},
                {9, 3, 0,  0, 0, 0,  0, 0, 0},

                {0, 0, 0,  0, 6, 0,  0, 0, 0},
                {3, 6, 2,  1, 0, 0,  0, 0, 0},
                {0, 0, 7,  9, 0, 5,  0, 0, 2}
        };
    }

    private static int[][] generateBoardHardest() {
        return new int[][]{
                {8, 0, 0,  0, 0, 0,  0, 0, 0},
                {0, 0, 3,  6, 0, 0,  0, 0, 0},
                {0, 7, 0,  0, 9, 0,  2, 0, 0},

                {0, 5, 0,  0, 0, 7,  0, 0, 0},
                {0, 0, 0,  0, 4, 5,  7, 0, 0},
                {0, 0, 0,  1, 0, 0,  0, 3, 0},

                {0, 0, 1,  0, 0, 0,  0, 6, 8},
                {0, 0, 8,  5, 0, 0,  0, 1, 0},
                {0, 9, 0,  0, 0, 0,  4, 0, 0}
        };
    }
}
