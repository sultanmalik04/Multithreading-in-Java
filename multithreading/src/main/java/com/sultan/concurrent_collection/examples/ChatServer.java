package com.sultan.concurrent_collection.examples;

import java.util.concurrent.*;
import java.util.*;

public class ChatServer {
    // Active users: username → User object
    private final ConcurrentHashMap<String, User> activeUsers = new ConcurrentHashMap<>();

    // Incoming messages queue
    private final ConcurrentLinkedQueue<Message> messageQueue = new ConcurrentLinkedQueue<>();

    // Thread pool for message processing
    private final ExecutorService messageProcessor = Executors.newFixedThreadPool(4);

    // Start message processing loop
    public void start() {
        Runnable processorTask = () -> {
            while (true) {
                Message msg = messageQueue.poll();
                if (msg != null) {
                    deliverMessage(msg);
                } else {
                    try {
                        Thread.sleep(10); // avoid busy-waiting
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }
        };

        // Start multiple processors
        for (int i = 0; i < 4; i++) {
            messageProcessor.submit(processorTask);
        }
    }

    // Add a user
    public void connectUser(String username) {
        activeUsers.putIfAbsent(username, new User(username));
        System.out.println(username + " connected.");
    }

    // Remove a user
    public void disconnectUser(String username) {
        activeUsers.remove(username);
        System.out.println(username + " disconnected.");
    }

    // Receive a message
    public void receiveMessage(String from, String to, String content) {
        messageQueue.offer(new Message(from, to, content));
    }

    // Deliver message to recipient
    private void deliverMessage(Message msg) {
        User recipient = activeUsers.get(msg.to);
        if (recipient != null) {
            recipient.receive(msg);
        } else {
            System.out.println("User " + msg.to + " not found.");
        }
    }
}