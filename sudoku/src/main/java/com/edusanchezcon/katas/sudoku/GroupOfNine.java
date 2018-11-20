package com.edusanchezcon.katas.sudoku;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GroupOfNine {

    private static final Set<Integer> ALL_NUMBERS = IntStream.rangeClosed(1,9).boxed().collect(Collectors.toSet());

    private Set<Integer> remainingElements = new HashSet<>(ALL_NUMBERS);

    public GroupOfNine(int[] numbers){
        remainingElements.removeAll( Arrays.stream(numbers).boxed().collect(Collectors.toSet()));
    }

    public boolean isSolved(){
        return remainingElements.isEmpty();
    }

    public Set<Integer> get() {
        return remainingElements;
    }

    @Override
    public String toString() {
        return remainingElements.toString();
    }
}
