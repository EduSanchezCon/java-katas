package com.edusanchezcon.katas.fizzbuzz;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FizzBuzzTest {

    @Test
    public void shouldWriteReceivedNumbers(){

        final List<String> actualResponse = FizzBuzz.transformList(Arrays.asList(1, 2));

        assertEquals(Arrays.asList("1", "2"), actualResponse);
    }

    @Test
    public void shouldWriteFizzWhenNumberIsDivisibleBy3(){

        final List<String> actualResponse = FizzBuzz.transformList(Arrays.asList(1,2,3));
        List<String> expectedList = Arrays.asList("1", "2", "Fizz");

        assertEquals(expectedList, actualResponse);
    }

    @Test
    public void shouldWriteBuzzWhenNumberIsDivisibleBy5(){

        final List<String> actualResponse = FizzBuzz.transformList(Arrays.asList(1,2,3,4,5));
        List<String> expectedList = Arrays.asList("1", "2", "Fizz", "4", "Buzz");

        assertEquals(expectedList, actualResponse);
    }

    @Test
    public void shouldWriteFizzBuzzWhenNumberIsDivisibleBy3And5(){

        final List<String> actualResponse = FizzBuzz.transformList(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15));
        List<String> expectedList = Arrays.asList(
                "1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11", "Fizz", "13", "14", "FizzBuzz");

        assertEquals(expectedList, actualResponse);
    }
}
