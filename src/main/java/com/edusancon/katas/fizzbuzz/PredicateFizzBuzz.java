package com.edusancon.katas.fizzbuzz;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PredicateFizzBuzz
{

    private Map<Predicate<Integer>, String> conditions = new LinkedHashMap<>();

    public PredicateFizzBuzz() {
    }

    public void addCondition(Predicate<Integer> condition, String transform) {
        conditions.put(condition, transform);
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


    public List<String> transformList(List<Integer> originList) {

        List<String> transformedList = new ArrayList<>();

        for (Integer i : originList){
            transformedList.add( transform(i) );
        }

        return  transformedList;
    }

}
