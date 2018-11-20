package com.edusanchezcon.katas.sudoku;

import com.edusanchezcon.katas.sudoku.SudokuBoard;
import com.edusanchezcon.katas.sudoku.mother.SudokuBoardMother;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class SudokuBoardPerfTest {

    @Test
    public void performanceOfVerifyingCorrectness(){

        final SudokuBoard board = SudokuBoardMother.solvedBoard();

        long totalElapsedTime = 0;

        for (int i=0; i<50; i++) {
            final long init = System.nanoTime();

            IntStream.rangeClosed(1, 100).forEach(n -> board.isSolved());
            long elapsed = (System.nanoTime() - init) / 100;

            System.out.println("Mean elapsed time: " + elapsed);
            if (i > 0) totalElapsedTime += elapsed;
        }

        System.out.println("Total elapsed time: " + (totalElapsedTime/49));
    }
}
