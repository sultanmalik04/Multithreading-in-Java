package com.sultan.concurrent_collection.examples;

class User {
    private final String username;

    public User(String username) {
        this.username = username;
    }

    public void receive(Message msg) {
        System.out.println("[" + username + "] received from " + msg.from + ": " + msg.content);
    }
}