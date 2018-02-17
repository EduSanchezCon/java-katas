package com.edusanchezcon.katas.fizzbuzz;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateFizzBuzz
{

    private final Map<Predicate<Integer>, String> conditions = new LinkedHashMap<>();


    public void addCondition(Predicate<Integer> condition, String transform) {

        conditions.put(condition, transform);
    }

    public List<String> transformList(List<Integer> originList) {

        return originList.stream()
                .map(this::transform)
                .collect(Collectors.toList());
    }

    private String transform(final Integer i){

        String transformed =  conditions.entrySet().stream()
                .filter(e -> e.getKey().test(i))
                .reduce(
                        "",
                        (r,e) -> r+e.getValue(),
                        (a,b) -> a
                );

        return transformed.equals("") ? String.valueOf(i) : transformed;
    }

}
