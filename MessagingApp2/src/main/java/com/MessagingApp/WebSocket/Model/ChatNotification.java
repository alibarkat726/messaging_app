package com.MessagingApp.WebSocket.Model;

import lombok.*;

public class ChatNotification {
    private String id;
    private String senderId;
    private String recipientId;
    private String content;

    public ChatNotification() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public ChatNotification(String content, String recipientId, String senderId, String id) {
        this.content = content;
        this.recipientId = recipientId;
        this.senderId = senderId;
        this.id = id;
    }

    private ChatNotification(Builder builder) {
        this.id = builder.id;
        this.senderId = builder.senderId;
        this.recipientId = builder.recipientId;
        this.content = builder.content;
    }

    // Static method to start the builder
    public static Builder builder() {
        return new Builder();
    }

    // Static nested Builder class
    public static class Builder {
        private String id;
        private String senderId;
        private String recipientId;
        private String content;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder senderId(String senderId) {
            this.senderId = senderId;
            return this;
        }

        public Builder recipientId(String recipientId) {
            this.recipientId = recipientId;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public ChatNotification build() {
            return new ChatNotification(this);
        }
    }

}
