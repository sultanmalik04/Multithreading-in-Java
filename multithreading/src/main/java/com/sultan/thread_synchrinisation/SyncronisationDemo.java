package com.sultan.thread_synchrinisation;

public class SyncronisationDemo {

    private static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread one = new Thread(() -> {
            for (int i = 0; i < 20000; i++) {
                increament();
            }
        });

        Thread two = new Thread(() -> {
            for (int i = 0; i < 20000; i++) {
                increament();
            }
        });

        one.start();
        two.start();

        one.join();
        two.join();

        System.out.println("Counter Value: " + counter);
    }

    private synchronized static void increament() {
        counter++;
    }
}

/*
 * Non atomic operation shared resource:
 * 1. load
 * 2. increament
 * 3. set back the value
 * 
 * -- Race Condition
 * 
 * -- Mutual Exclusion
 * can be achieved with the help of syncronized key word
 * there is problem with syncronized keyword at method level as it tries to
 * accuire the monitor lock(Intrinsic lock)
 * 
 */