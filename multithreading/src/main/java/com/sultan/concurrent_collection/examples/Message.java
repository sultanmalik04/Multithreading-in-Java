package com.sultan.concurrent_collection.examples;

class Message {
    public final String from;
    public final String to;
    public final String content;

    public Message(String from, String to, String content) {
        this.from = from;
        this.to = to;
        this.content = content;
    }
}