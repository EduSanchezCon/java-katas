package com.edusanchezcon.katas.fizzbuzz;

import java.util.stream.IntStream;

public class Sumas {

    public static void main(String[] args) {
        final int sum = IntStream.rangeClosed(1, 1000).filter(i -> i >= 500 || i % 2 == 0).sum();
        System.out.println(sum);
    }
}
