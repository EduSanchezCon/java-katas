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

    def "given an index should return the correspondent cell value"(){

        expect:
        sut.getCellValue(i) == v

        where:
        i << [0,1,2,78,79,80]
        v << [9,6,7, 8, 9, 2]
    }

    def "given an index should return the associated line"(){

        setup:
        def sut =  SudokuBoardMother.incompleteBoard();

        expect:
        sut.getLineAtIndex(i).get() == l.get()

        where:
        i << [0,1,9,71,79,80]
        l << [new GroupOfNine([0, 0, 7,    0, 8, 0,    0, 0, 0] as int[]),
              new GroupOfNine([0, 0, 7,    0, 8, 0,    0, 0, 0] as int[]),
              new GroupOfNine([0, 0, 3,    9, 2, 0,    6, 0, 0] as int[]),
              new GroupOfNine([0, 0, 5,    0, 9, 8,    4, 0, 0] as int[]),
              new GroupOfNine([0, 0, 0,    0, 5, 0,    8, 0, 0] as int[]),
              new GroupOfNine([0, 0, 0,    0, 5, 0,    8, 0, 0] as int[])]
    }

    def "given an index should return the associated column"(){

        setup:
        def sut =  SudokuBoardMother.incompleteBoard();

        expect:
        sut.getColumnAtIndex(i).get() == l.get()

        where:
        i << [0,9,10,70,79,80]
        l << [new GroupOfNine([0, 0, 2,    6, 0, 5,    0, 0, 0] as int[]),
              new GroupOfNine([0, 0, 2,    6, 0, 5,    0, 0, 0] as int[]),
              new GroupOfNine([0, 0, 4,    2, 8, 0,    0, 0, 0] as int[]),
              new GroupOfNine([0, 0, 0,    0, 2, 4,    1, 0, 0] as int[]),
              new GroupOfNine([0, 0, 0,    0, 2, 4,    1, 0, 0] as int[]),
              new GroupOfNine([0, 0, 0,    1, 0, 8,    3, 0, 0] as int[])]
    }

    def "given an index should return the associated quadrant"(){

        setup:
        def sut =  SudokuBoardMother.incompleteBoard();

        expect:
        sut.getQuadrantAtIndex(i).get() == l.get()

        where:
        i << [0,20,26,59,60,80]
        l << [new GroupOfNine([0, 0, 7,    0, 0, 3,    2, 4, 0] as int[]),
              new GroupOfNine([0, 0, 7,    0, 0, 3,    2, 4, 0] as int[]),
              new GroupOfNine([0, 0, 0,    6, 0, 0,    0, 0, 0] as int[]),
              new GroupOfNine([4, 7, 6,    0, 9, 8,    0, 5, 0] as int[]),
              new GroupOfNine([0, 1, 3,    4, 0, 0,    8, 0, 0] as int[]),
              new GroupOfNine([0, 1, 3,    4, 0, 0,    8, 0, 0] as int[])]
    }

    def "when set cell value should update remaining sets of numbers"(){

        setup:
        def sut =  SudokuBoardMother.incompleteBoard()

        when:
        sut.setCellValue(33, 7)

        then:
        sut.getLineAtIndex(33).get() == [3,4,5,8] as Set<Integer>
        sut.getColumnAtIndex(33).get() == [1,2,3,5] as Set<Integer>
        sut.getQuadrantAtIndex(33).get() == [3,5,6] as Set<Integer>
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
