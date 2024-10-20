package com.sultan.thread_synchrinisation;

public class WaitAndNotifyDemo {
    private static final Object LOCK = new Object();

    public static void main(String[] args) {
        Thread one = new Thread(() -> {
            try {
                one();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread two = new Thread(() -> {
            try {
                two();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        one.start();
        two.start();
    }

    private static void one() throws InterruptedException {
        synchronized (LOCK) {
            System.out.println("Hello from method one....");
            LOCK.wait();
            System.out.println("Back again from the method one...");
        }
    }

    private static void two() throws InterruptedException {
        synchronized (LOCK) {
            System.out.println("Hello from method two....");
            LOCK.notify();
            System.out.println("hello from method two after notify...");
        }
    }
}

/*
 * Wait and notify is used for inter thread communication
 */