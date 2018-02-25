package com.edusanchezcon.katas.sudoku

import com.edusanchezcon.katas.sudoku.mother.SudokuBoardMother
import spock.lang.Specification

class SudokuBoardTest extends Specification {

    def sut =  SudokuBoardMother.solvedBoard();

    def "verify that the board is solved"(){

        expect:
            sut.isSolved() == true
    }

    def "given an incomplete board verify that it's not solved"(){

        setup:
        def sut =  SudokuBoardMother.incompleteBoard();

        expect:
        sut.isSolved() == false
    }

    def "should return a specific line"(){

        expect:
        sut.getLine(2) == [2, 4, 8,   6, 3, 5,   1, 7, 9]
    }

    def "should return a specific column"(){

        expect:
        sut.getColumn(2) == [7, 3, 8,   9, 4, 1,   2, 5, 6]
    }

    def "should return a specific quadrant"(){

        expect:
        sut.getQuadrant(i) == q

        where:
        i << [4, 6]
        q << [[8, 4, 3,   5, 1, 9,   7, 6, 2],
              [8, 9, 2,   3, 1, 5,   4, 7, 6]]
    }

    def "should test if 9 numbers are not repeated"(){

        expect:
        SudokuBoard.isPartialCompleted(n) == r

        where:
        n << [[1,2,3,4,5,6,7,8,9] as int[],
              [8,4,3,5,1,9,7,6,2] as int[],
              [8,9,2,3,1,5,4,7,6] as int[],
              [1,2,3,4,5,3,7,8,9] as int[],
              [2,3,4,5,6,7,9,9,0] as int[]]
        r << [true, true, true, false, false]
    }
/*
    def "should test if numbers are not repeated"(){

        expect:
        SudokuBoard.isPartialCompletedBit(n) == r

        where:
        n << [[1,2,3,4,5,6,7,8,9] as int[],
              [8,4,3,5,1,9,7,6,2] as int[],
              [8,9,2,3,1,5,4,7,6] as int[],
              [1,2,3,4,5,3,7,8,9] as int[],
              [2,3,4,5,6,7,9,9,0] as int[]]
        r << [true, true, true, false, false]
    }
*/
    /*
    def "given a position should return all its possible values"(){

        setup:
        def sut =  SudokuBoardMother.incompleteBoard();

        expect:
        sut.getCandidates(36) == [3,4,7] as Set;
    }
    */
}
