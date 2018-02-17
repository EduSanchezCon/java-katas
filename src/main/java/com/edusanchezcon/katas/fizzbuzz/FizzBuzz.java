package com.edusanchezcon.katas.fizzbuzz;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FizzBuzz
{

    public List<String> transformList(List<Integer> originList){
        return originList.stream()
                .map(this::transform)
                .collect(Collectors.toList());
    }


    protected String transform(Integer i){

        String response = "";

        if (i % 3 == 0) response = "Fizz";
        if (i % 5 == 0) response += "Buzz";

        return response.length()==0 ? String.valueOf(i) : response;
    }


}
