package org.example.core;


public class Request {
    private final String messageText;
    private final String userId;

    public Request(String messageText, String userId) {
        this.messageText = messageText;
        this.userId = userId;
    }

    public String getMessageText() {
        return this.messageText;
    }

    public String getUserId() {
        return this.userId;
    }
}
