package com.sultan;

public class DeamonUserThreadDemo {
    public static void main(String[] args) {
        Thread bgThread = new Thread(new DeamonHelper());
        Thread userThread = new Thread(new UserThreadHelper());
        bgThread.setDaemon(true);

        bgThread.start();
        userThread.start();
    }
}

class DeamonHelper implements Runnable {
    @Override
    public void run() {
        int count = 0;
        while (count < 500) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
            System.out.println("Deamon helper running...");
        }
    }
}

class UserThreadHelper implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        System.out.println("User thread done with execution...");
    }
}

// deamon threads automatically get completed a soon as all
// user threads complete their execution