package com.sultan;

class JoinThreadExample {
    public static void main(String[] args) throws InterruptedException {
        Thread one = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread1 :  " + i);
            }
        });

        Thread two = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread2 :  " + i);
            }
        });

        System.out.println("Before executing the threads...");
        one.start();
        two.start();

        one.join();
        two.join();

        System.out.println("Done executing the threads!");
    }
}

/*
 * output without using join mathod:
 * Done executing the threads!
 * Thread1 : 0
 * Thread1 : 1
 * Thread1 : 2
 * Thread1 : 3
 * Thread1 : 4
 * Thread2 : 0
 * Thread2 : 1
 * Thread2 : 2
 * Thread2 : 3
 * Thread2 : 4
 */