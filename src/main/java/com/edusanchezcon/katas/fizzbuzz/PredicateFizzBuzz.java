package com.edusanchezcon.katas.fizzbuzz;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateFizzBuzz extends FizzBuzz
{

    private final Map<Predicate<Integer>, String> conditions = new LinkedHashMap<>();


    public void addCondition(Predicate<Integer> condition, String transform) {

        conditions.put(condition, transform);
    }

    @Override
    protected String transform(final Integer i){

        String transformed =  conditions.entrySet().stream()
                .filter(entry -> entry.getKey().test(i))
                .reduce(
                        "",
                        (accumulator, entry) -> accumulator + entry.getValue(),
                        (a,b) -> a
                );

        return transformed.equals("") ? String.valueOf(i) : transformed;
    }

}
