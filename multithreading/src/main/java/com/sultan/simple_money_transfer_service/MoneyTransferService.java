package com.sultan.simple_money_transfer_service;

import java.util.concurrent.atomic.AtomicInteger;

public class MoneyTransferService {

    private static class Account {
        private AtomicInteger balance;

        public Account(int initialBalance) {
            balance = new AtomicInteger(initialBalance);
        }

        public boolean transfer(Account toAccount, int amount) {
            int currentBalance = balance.get();

            if (currentBalance < amount) {
                System.out.println("Insufficient funds");
                return false; // Insufficient funds
            }

            // Attempt to withdraw using CAS
            boolean successfulWithdrawal = balance.compareAndSet(currentBalance, currentBalance - amount);

            if (successfulWithdrawal) {
                toAccount.deposit(amount);
            }

            return successfulWithdrawal;
        }

        public void deposit(int amount) {
            balance.getAndAdd(amount);
        }

        public int getBalance() {
            return balance.get();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Account account1 = new Account(1000);
        Account account2 = new Account(1000);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 200; i++) {
                account1.transfer(account2, 10);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 200; i++) {
                account2.transfer(account1, 10);
            }
        });

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                if (i % 2 == 0) {
                    account2.transfer(account1, 20);
                } else {
                    account1.transfer(account2, 10);
                }

            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Account 1 balance: " + account1.getBalance());
        System.out.println("Account 2 balance: " + account2.getBalance());

        t3.start();
        t3.join();

        System.out.println("Account 1 balance: " + account1.getBalance());
        System.out.println("Account 2 balance: " + account2.getBalance());
    }
}