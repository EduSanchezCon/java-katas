package com.edusanchezcon.katas.fizzbuzz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FizzBuzzTest {

    private FizzBuzz fizzBuzz;

    @BeforeEach
    public void init(){
        fizzBuzz = new FizzBuzz();
    }

    @Test
    public void shouldWriteReceivedNumbers(){

        final List<String> actualResponse = fizzBuzz.transformList(Arrays.asList(1, 2));

        assertEquals(Arrays.asList("1", "2"), actualResponse);
    }

    @Test
    public void shouldWriteFizzWhenNumberIsDivisibleBy3(){

        assertEquals("Fizz", fizzBuzz.transform(6));
    }


    @Test
    public void shouldWriteBuzzWhenNumberIsDivisibleBy5(){

        assertEquals("Buzz", fizzBuzz.transform(10));
    }

    @Test
    public void shouldWriteFizzBuzzWhenNumberIsDivisibleBy3And5(){

        assertEquals("FizzBuzz", fizzBuzz.transform(15));
    }

    @Test
    public void shouldWriteAdecuateOutputForNumbersFrom1To15(){

        final List<String> actualResponse = new FizzBuzz().transformList(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15));
        List<String> expectedList = Arrays.asList(
                "1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11", "Fizz", "13", "14", "FizzBuzz");

        assertEquals(expectedList, actualResponse);
    }
}
