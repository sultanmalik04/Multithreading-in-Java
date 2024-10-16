package com.sultan.thread_synchrinisation;

public class LockWithCustomObjects {
    private static int counter1 = 0;
    private static int counter2 = 0;

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread one = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increament1();
            }
        });

        Thread two = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increament2();
            }
        });

        one.start();
        two.start();

        one.join();
        two.join();

        System.out.println("counter1: " + counter1 + " -- counter2: " + counter2);
    }

    private static void increament2() {
        synchronized (lock1) {
            counter1++;
        }
    }

    private static void increament1() {
        synchronized (lock2) {
            counter2++;
        }
    }

}
