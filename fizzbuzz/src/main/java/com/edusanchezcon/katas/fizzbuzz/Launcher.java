package com.edusanchezcon.katas.fizzbuzz;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Launcher {

    public static void main (String... args){

        final List<Integer> firstHundred = IntStream.rangeClosed(1, 100).boxed().collect(Collectors.toList());

        List<String> transformedList = generateFizzBuzz().transformList(firstHundred);
        System.out.println( transformedList.stream().collect(Collectors.joining(", ")) );

        List<String> transformedList2 = generatePssFizzBuzz().transformList(firstHundred);
        System.out.println( transformedList2.stream().collect(Collectors.joining(", ")) );

        List<String> transformedList3 = generatePssFizzBuzzMeh().transformList(firstHundred);
        System.out.println( transformedList3.stream().collect(Collectors.joining(", ")) );
    }

    private static FizzBuzz generateFizzBuzz(){
        return new FizzBuzz();
    }

    private static FizzBuzz generatePssFizzBuzz(){

        final ParameterizedFizzBuzz fizzBuzz = new ParameterizedFizzBuzz();
        fizzBuzz.addCondition(2, "Pss");
        fizzBuzz.addCondition(3, "Fizz");
        fizzBuzz.addCondition(5, "Buzz");

        return  fizzBuzz;
    }

    private static FizzBuzz generatePssFizzBuzzMeh(){

        final PredicateFizzBuzz fizzBuzz = new PredicateFizzBuzz();

        fizzBuzz.addCondition(i -> i%2 ==0, "Pss");
        fizzBuzz.addCondition(i -> i%3 ==0, "Fizz");
        fizzBuzz.addCondition(i -> i%5 ==0, "Buzz");
        fizzBuzz.addCondition(i -> String.valueOf(i).contains("4"), "Meh");

        return  fizzBuzz;
    }
}
