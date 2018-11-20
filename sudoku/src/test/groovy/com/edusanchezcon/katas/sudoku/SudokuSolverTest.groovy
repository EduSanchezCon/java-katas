package com.edusanchezcon.katas.sudoku

import com.edusanchezcon.katas.sudoku.mother.SudokuBoardMother
import org.apache.commons.collections4.SetUtils
import spock.lang.Specification

class SudokuSolverTest extends Specification{


    def "given an incomplete board should return a solved board"(){

        setup:
        def sut = new SudokuSolver(SudokuBoardMother.incompleteBoard())

        when:
        def solvedBoard = sut.solve()
        System.out.println(solvedBoard.toString())

        then:
        solvedBoard.isSolved();
    }

    def "set intersections"(){

        setup:
        def line = [2,3,4,5,6,8] as Set
        def column = [3,6,8,9] as Set
        def quadrant = [3,7,8,9] as Set

        when:
        def candidates = SetUtils.intersection(
                            SetUtils.intersection(line, column),
                            quadrant)

        then:
        candidates == [3,8] as Set
    }

}
