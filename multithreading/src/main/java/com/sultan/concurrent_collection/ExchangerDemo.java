package com.sultan.concurrent_collection;

import java.util.concurrent.Exchanger;

public class ExchangerDemo {
    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();

        Thread one = new Thread(new FirstThread(exchanger));
        Thread two = new Thread(new SecondThread(exchanger));

        one.start();
        two.start();
    }
}

class FirstThread implements Runnable {
    private final Exchanger<Integer> exchanger;

    public FirstThread(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        int dataToSend = 10;
        System.out.println("First thread is sending a data " + dataToSend);

        try {
            Integer receivedData = exchanger.exchange(dataToSend);
            System.out.println("First thread received data " + receivedData);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}

class SecondThread implements Runnable {
    private final Exchanger<Integer> exchanger;

    public SecondThread(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            int dataToSend = 20;
            System.out.println("Second thread is sending a data " + dataToSend);
            Integer receivedData = exchanger.exchange(dataToSend);
            System.out.println("Second thread received data " + receivedData);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}