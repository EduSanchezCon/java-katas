package com.edusanchezcon.katas.sudoku;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class GroupOfNine {

    private Set<Integer> innerSet;

    public GroupOfNine(int[] numbers){
        innerSet = Arrays.stream(numbers).boxed().collect(Collectors.toSet());
    }

    public boolean isSolved(){
        return innerSet.size() == 9;
    }
}
