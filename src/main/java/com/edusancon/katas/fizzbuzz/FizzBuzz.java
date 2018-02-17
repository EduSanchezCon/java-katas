package com.edusancon.katas.fizzbuzz;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FizzBuzz
{

    public FizzBuzz() {
    }

    public static String generate(int first, int last){
        return IntStream.rangeClosed(first, last).boxed()

                .map(FizzBuzz::transform)
                .collect(Collectors.joining(", "));
    }


    private static List<Integer> generateList(int first, int last){

        return IntStream.rangeClosed(first, last).boxed().collect(Collectors.toList());
    }

    private static String transform(Integer i){
        if (i % 3 > 0 && i % 5 > 0) return String.valueOf(i);
        String response = "";
        if (i % 3 == 0) response = "Fizz";
        if (i % 5 == 0) response += "Buzz";
        return response;
    }


}
