package com.edusanchezcon.katas.fizzbuzz;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParameterizedFizzBuzz
{

    private final Map<Integer, String> conditions = new LinkedHashMap<>();


    public void addCondition(int i, String transform) {

        conditions.put(i, transform);
    }

    public List<String> transformList(List<Integer> originList) {

        return originList.stream()
                .map(this::transform)
                .collect(Collectors.toList());
    }

    private String transform(final Integer i){

        String transformed =  conditions.entrySet().stream()
                .filter(e -> i % e.getKey() == 0)
                .reduce(
                        "",
                        (r,e) -> r+e.getValue(),
                        (a,b) -> a
                );

        return transformed.equals("") ? String.valueOf(i) : transformed;
    }

}
