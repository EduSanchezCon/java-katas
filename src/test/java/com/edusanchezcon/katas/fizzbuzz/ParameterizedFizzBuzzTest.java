package com.edusanchezcon.katas.fizzbuzz;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParameterizedFizzBuzzTest {

    @Test
    public void givenNoConditionsThenShouldReturnTheSameList(){

        final ParameterizedFizzBuzz fizzBuzz = new ParameterizedFizzBuzz();

        final List<Integer> originList = Arrays.asList(1, 2, 3, 4, 5);
        List<String> actualList = fizzBuzz.transformList(originList);

        List<String> expectedList = Arrays.asList("1", "2", "3", "4", "5");

        MatcherAssert.assertThat(actualList, Matchers.is(expectedList));

    }

    @Test
    public void given3FizzConditionThenShouldReturnFizzOnMultiplesOf3(){

        final ParameterizedFizzBuzz fizzBuzz = new ParameterizedFizzBuzz();
        fizzBuzz.addCondition(3, "Fizz");

        final List<Integer> originList = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<String> actualList = fizzBuzz.transformList(originList);

        List<String> expectedList = Arrays.asList("1", "2", "Fizz", "4", "5", "Fizz");

        MatcherAssert.assertThat(actualList, Matchers.is(expectedList));

    }

    @Test
    public void givenFizzBuzzConditionsThenShouldReturnOriginalFizzBuzz(){

        final ParameterizedFizzBuzz fizzBuzz = new ParameterizedFizzBuzz();
        fizzBuzz.addCondition(3, "Fizz");
        fizzBuzz.addCondition(5, "Buzz");

        final List<Integer> originList = IntStream.rangeClosed(1,15).boxed().collect(Collectors.toList());
        List<String> actualList = fizzBuzz.transformList(originList);

        List<String> expectedList = Arrays.asList(
                "1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11", "Fizz", "13", "14", "FizzBuzz");

        MatcherAssert.assertThat(actualList, Matchers.is(expectedList));

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

        MatcherAssert.assertThat(actualList, Matchers.is(expectedList));

    }
}
