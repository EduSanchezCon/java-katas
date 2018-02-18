package com.edusanchezcon.katas.fizzbuzz;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class PredicateFizzBuzzTest {

    private PredicateFizzBuzz fizzBuzz;

    @BeforeEach
    public void  init(){
        fizzBuzz = new PredicateFizzBuzz();

        fizzBuzz.addCondition(i -> i%2 ==0, "Pss");
        fizzBuzz.addCondition(i -> i%3 ==0, "Fizz");
        fizzBuzz.addCondition(i -> i%5 ==0, "Buzz");
        fizzBuzz.addCondition(i -> String.valueOf(i).contains("4"), "Meh");
    }

    @Test
    public void givenANumberWithDigit4ThenShouldReturnMeh(){

        assertEquals("Meh", fizzBuzz.transform(47));
    }

    @Test
    public void givenFirst15NumbersShouldReturnAdequateStrings(){

        final List<Integer> originList = IntStream.rangeClosed(1,15).boxed().collect(Collectors.toList());
        List<String> actualList = fizzBuzz.transformList(originList);

        List<String> expectedList = Arrays.asList(
                "1", "Pss", "Fizz", "PssMeh", "Buzz", "PssFizz", "7", "Pss", "Fizz", "PssBuzz", "11",
                "PssFizz", "13", "PssMeh", "FizzBuzz");

        assertThat(actualList, Matchers.is(expectedList));

    }
}
