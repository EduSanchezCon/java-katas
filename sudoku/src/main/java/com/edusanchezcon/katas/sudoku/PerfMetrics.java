package com.edusanchezcon.katas.sudoku;

public class PerfMetrics {

    private final int warmup;
    private int reps;
    private long totalNanos;

    public PerfMetrics(int warmup, int reps) {
        this.warmup = warmup;
        this.reps = reps;
    }

    public void measure(Runnable runnable){
        for (int i = 0; i < reps; i++) {
            final long start = System.currentTimeMillis();
            runnable.run();
            final long timeSpent = System.currentTimeMillis() - start;

            if (i >= warmup) totalNanos += timeSpent;
        }
    }

    private int getValidReps(){
        return reps - warmup;
    }

    private double getAverage(){
        return totalNanos / getValidReps();
    }

    @Override
    public String toString() {
        return "Average: " + getAverage() + " | Repetitions: " + getValidReps();
    }
}
