package edu.umb.cs681.hw05;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BostonHousingMultiThreaded {
    private static final int NUM_THREADS = 4;

    public static void main(String[] args) {
        // Load data
        List<String[]> data = BostonHousing.parseCsvFile("bos-housing.csv");

        // Create executor service with 4 threads
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        // Create 4 data processing tasks with 1 extra thread each
        Runnable task1 = () -> {
            BostonHousing.dataProcessing1(data);
        };

        Runnable task2 = () -> {
            BostonHousing.dataProcessing2(data);
        };

        Runnable task3 = () -> {
            BostonHousing.dataProcessing3(data);
        };

        Runnable task4 = () -> {
            BostonHousing.dataProcessing4(data);
        };

        // Submit tasks to executor service
        executor.submit(task1);
        executor.submit(task2);
        executor.submit(task3);
        executor.submit(task4);

        // Shutdown executor and wait for all tasks to complete
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            System.err.println("Interrupted while waiting for tasks to complete: " + e.getMessage());
        }

//        // Collect results from tasks
//        double result1 = BostonHousing.getResult1();
//        double result2 = BostonHousing.getResult2();
//        double[] result3 = BostonHousing.getResult3();
//        double[] result4 = BostonHousing.getResult4();
//
    }
}
