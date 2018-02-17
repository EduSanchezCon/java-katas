package com.edusancon.katas.fizzbuzz;

import com.edusancon.katas.fizzbuzz.FizzBuzz;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FizzBuzzTest {

    @Test
    public void shouldWriteReceivedNumbers(){

        final String actualResponse = FizzBuzz.generate(1, 2);

        assertEquals("1, 2", actualResponse);
    }

    @Test
    public void shouldWriteFizzWhenNumberIsDivisibleBy3(){

        final String actualResponse = FizzBuzz.generate(1,3);

        assertEquals("1, 2, Fizz", actualResponse);
    }

    @Test
    public void shouldWriteBuzzWhenNumberIsDivisibleBy5(){

        final String actualResponse = FizzBuzz.generate(1,5);

        assertEquals("1, 2, Fizz, 4, Buzz", actualResponse);
    }

    @Test
    public void shouldWriteFizzBuzzWhenNumberIsDivisibleBy3And5(){

        final String actualResponse = FizzBuzz.generate(1,15);

        assertEquals("1, 2, Fizz, 4, Buzz, Fizz, 7, 8, Fizz, Buzz, 11, Fizz, 13, 14, FizzBuzz", actualResponse);
    }
}
