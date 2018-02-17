package com.edusanchezcon.katas.fizzbuzz;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParameterizedFizzBuzz
{

    private final Map<Integer, String> conditions = new LinkedHashMap<>();


    public void addCondition(int i, String transform) {

        conditions.put(i, transform);
    }

    public List<String> transformList(List<Integer> originList) {

        List<String> transformedList = new ArrayList<>();

        for (Integer i : originList){
            transformedList.add( transform(i) );
        }

        return  transformedList;
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
