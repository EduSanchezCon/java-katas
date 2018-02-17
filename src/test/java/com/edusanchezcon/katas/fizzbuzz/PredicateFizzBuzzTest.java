package com.edusanchezcon.katas.fizzbuzz;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PredicateFizzBuzzTest {

    @Test
    public void givenSomeConditionsThenShouldReturnTransformationsWhenMatched(){

        final PredicateFizzBuzz fizzBuzz = new PredicateFizzBuzz();

        fizzBuzz.addCondition(i -> i%2 ==0, "Pss");
        fizzBuzz.addCondition(i -> i%3 ==0, "Fizz");
        fizzBuzz.addCondition(i -> i%5 ==0, "Buzz");
        fizzBuzz.addCondition(i -> i > 10, "Plof");

        final List<Integer> originList = IntStream.rangeClosed(1,15).boxed().collect(Collectors.toList());
        List<String> actualList = fizzBuzz.transformList(originList);

        List<String> expectedList = Arrays.asList(
                "1", "Pss", "Fizz", "Pss", "Buzz", "PssFizz", "7", "Pss", "Fizz", "PssBuzz", "Plof",
                "PssFizzPlof", "Plof", "PssPlof", "FizzBuzzPlof");

        MatcherAssert.assertThat(actualList, Matchers.is(expectedList));

    }
}
