package com.sultan;

public class ThreadPriorityExample {
    public static void main(String[] args) {
        // System.out.println(Thread.currentThread().getName());
        // System.out.println(Thread.currentThread().getPriority());
        // Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        // System.out.println(Thread.currentThread().getPriority());

        System.out.println(Thread.currentThread().getName() + " says hi!");

        Thread one = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " says Hi as well!");
        });

        one.setPriority(Thread.MAX_PRIORITY);
        one.start();
    }
}

// Thread priority
// MAX_PRIORITY == 10
// MIN_PRIORITY == 0
