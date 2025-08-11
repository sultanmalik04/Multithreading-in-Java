package com.sultan.concurrent_collection.examples;

public class Main {
    public static void main(String[] args) {
        ChatServer server = new ChatServer();
        server.start();

        server.connectUser("Alice");
        server.connectUser("Bob");

        server.receiveMessage("Alice", "Bob", "Hey Bob!");
        server.receiveMessage("Bob", "Alice", "Hi Alice!");
        server.receiveMessage("Alice", "Bob", "How are you?");
        server.receiveMessage("Bob", "Alice", "I'm good, thanks!");
        server.receiveMessage("Alice", "Bob", "Let's meet tomorrow.");
        server.receiveMessage("Bob", "Alice", "Sure, see you then!");

        // Simulate delay
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {
        }

        server.disconnectUser("Alice");
        server.disconnectUser("Bob");
    }
}
