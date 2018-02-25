package com.edusanchezcon.katas.sudoku

import spock.lang.Specification

class GroupOfNineTest extends Specification {


    def "verify that the group is partially solved"(){

        setup:
        def sut = new GroupOfNine([2, 4, 8,   6, 3, 5,   1, 7, 9] as int[])


        expect:
            sut.isSolved() == true
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
