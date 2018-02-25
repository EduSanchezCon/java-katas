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
        sut.collectLine(2) == [2, 4, 8,   6, 3, 5,   1, 7, 9]
    }

    def "should return a specific column"(){

        expect:
        sut.collectColumn(2) == [7, 3, 8,   9, 4, 1,   2, 5, 6]
    }

    def "should return a specific quadrant"(){

        expect:
        sut.collectQuadrant(i) == q

        where:
        i << [4, 6]
        q << [[8, 4, 3,   5, 1, 9,   7, 6, 2],
              [8, 9, 2,   3, 1, 5,   4, 7, 6]]
    }

    /*
    def "given a position should return all its possible values"(){

        setup:
        def sut =  SudokuBoardMother.incompleteBoard();

        expect:
        sut.getCandidates(36) == [3,4,7] as Set;
    }
    */
}
