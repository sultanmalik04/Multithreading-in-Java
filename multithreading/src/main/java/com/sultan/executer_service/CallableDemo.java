package com.sultan.executer_service;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CallableDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> result = executorService.submit(new ReturnValueTask());
        System.out.println("before printing result!");
        // System.out.println(result.get());
        // result.cancel(false);

        System.out.println("Is cancelled " + result.isCancelled());
        System.err.println("Is done " + result.isDone());
        System.out.println(result.get(6, TimeUnit.SECONDS));
        System.out.println("Is done " + result.isDone());
        System.out.println("Main thread execution completed!");
        executorService.shutdown();
    }
}

class ReturnValueTask implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        Thread.sleep(5000);
        return 1;
    }

}
