package com.sultan;

import java.util.concurrent.*;

public class CallableDemo {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<Integer> task = () -> {
            System.out.println("Calculating sum...");
            Thread.sleep(1000);
            return 10 + 20;
        };

        Future<Integer> future = executor.submit(task);

        System.out.println("Doing other work...");

        // This will block until result is ready
        Integer result = future.get();
        System.out.println("Result: " + result);

        executor.shutdown();
    }
}
