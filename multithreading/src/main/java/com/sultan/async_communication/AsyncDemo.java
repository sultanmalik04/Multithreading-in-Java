package com.sultan.async_communication;

import java.util.concurrent.*;

public class AsyncDemo {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("Rnning async task...");
            return "Hello from async!";
        }).thenAccept(result -> {
            System.out.println("Received: " + result);
        });

        System.out.println("Main thread doing other work...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
    }
}
