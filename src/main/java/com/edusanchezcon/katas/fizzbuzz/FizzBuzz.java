package com.edusanchezcon.katas.fizzbuzz;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FizzBuzz
{

    public static List<String> transformList(int first, int last){
        return IntStream.rangeClosed(first, last).boxed()
                .map(FizzBuzz::transform)
                .collect(Collectors.toList());
    }


    private static String transform(Integer i){

        String response = "";

        if (i % 3 == 0) response = "Fizz";
        if (i % 5 == 0) response += "Buzz";

        return response.length()==0 ? String.valueOf(i) : response;
    }


}
