package com.sultan;

public class RunnableThreadExample {
    public static void main(String[] args) {
        Thread one = new Thread(new ThreadOne());
        Thread two = new Thread(new ThreadTwo());
        // Lambda expression
        Thread three = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                System.out.println("Thread three : " + i);
            }
        });

        one.start();
        two.start();
        three.start();
    }
}

class ThreadOne implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println("Thread one : " + i);
        }
    }

}

class ThreadTwo implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println("Thread two : " + i);
        }
    }

}