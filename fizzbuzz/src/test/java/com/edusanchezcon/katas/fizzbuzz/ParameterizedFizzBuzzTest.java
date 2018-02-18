package com.edusanchezcon.katas.fizzbuzz;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

public class ParameterizedFizzBuzzTest {


    @Test
    public void givenNoConditionsThenShouldReturnTheSameList(){

        final ParameterizedFizzBuzz fizzBuzz = new ParameterizedFizzBuzz();

        final List<Integer> originList = Arrays.asList(1, 2, 3, 4, 5);
        List<String> actualList = fizzBuzz.transformList(originList);

        List<String> expectedList = Arrays.asList("1", "2", "3", "4", "5");

        assertThat(actualList, is(expectedList));

    }

    @Test
    public void given3FizzConditionThenShouldReturnFizzOnMultiplesOf3(){

        final ParameterizedFizzBuzz fizzBuzz = new ParameterizedFizzBuzz();
        fizzBuzz.addCondition(3, "Fizz");

        assertEquals("Fizz", fizzBuzz.transform(15));

    }

    @Test
    public void givenFizzBuzzConditionsThenShouldReturnFizzBuzzOnMultiplesOf3And5(){

        final ParameterizedFizzBuzz fizzBuzz = new ParameterizedFizzBuzz();
        fizzBuzz.addCondition(3, "Fizz");
        fizzBuzz.addCondition(5, "Buzz");

        assertEquals("FizzBuzz", fizzBuzz.transform(15));

    }

    @Test
    public void givenMultipleOfTwoShouldReturnPss(){
        final ParameterizedFizzBuzz fizzBuzz = new ParameterizedFizzBuzz();
        fizzBuzz.addCondition(2, "Pss");
        fizzBuzz.addCondition(3, "Fizz");
        fizzBuzz.addCondition(5, "Buzz");

        assertEquals("Pss", fizzBuzz.transform(4));
    }

    @Test
    public void givenPssFizzBuzzConditionsThenShouldReturnPssFizzBuzz(){

        final ParameterizedFizzBuzz fizzBuzz = new ParameterizedFizzBuzz();
        fizzBuzz.addCondition(2, "Pss");
        fizzBuzz.addCondition(3, "Fizz");
        fizzBuzz.addCondition(5, "Buzz");

        final List<Integer> originList = IntStream.rangeClosed(1,15).boxed().collect(Collectors.toList());
        List<String> actualList = fizzBuzz.transformList(originList);

        List<String> expectedList = Arrays.asList(
                "1", "Pss", "Fizz", "Pss", "Buzz", "PssFizz", "7", "Pss", "Fizz", "PssBuzz", "11", "PssFizz", "13", "Pss", "FizzBuzz");

        assertEquals(expectedList, actualList);

    }
}
