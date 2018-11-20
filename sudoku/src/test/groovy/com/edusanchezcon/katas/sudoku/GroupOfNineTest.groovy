package com.edusanchezcon.katas.sudoku

import spock.lang.Specification

class GroupOfNineTest extends Specification {


    def "verify that the group is partially solved"(){

        expect:
        new GroupOfNine(n).isSolved() == t

        where:
        n << [[1,2,3,4,5,6,7,8,9] as int[],
              [8,4,3,5,1,9,7,6,2] as int[],
              [8,9,2,3,1,5,4,7,6] as int[],
              [1,2,3,4,5,3,7,8,9] as int[],
              [2,3,4,5,6,7,9,9,0] as int[]]
        t << [true, true, true, false, false]
    }

    def "should show its remaining set of elements"(){

        expect:
        new GroupOfNine(n).get() == s

        where:
        n << [[1,2,3,4,5,6,7,8,9] as int[],
              [1,2,3,4,5,3,7,8,9] as int[],
              [2,0,4,0,6,0,9,0,0] as int[]]
        s << [[] as Set<Integer>,
              [6] as Set<Integer>,
              [1,3,5,7,8] as Set<Integer>]
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
