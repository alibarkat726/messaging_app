package com.MessagingApp.WebSocket.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data

@Builder
@Document
@AllArgsConstructor
public class ChatRoom {

    @Id
    private String id;
    private String chatId;
    private String senderId;
    private String recipientId;

    // Private constructor
    public ChatRoom(){};
    private ChatRoom(Builder builder) {
        this.id = builder.id;
        this.chatId = builder.chatId;
        this.senderId = builder.senderId;
        this.recipientId = builder.recipientId;
    }

    // Static method to start the builder
    public static Builder builder() {
        return new Builder();
    }

    public String getRecipientId() {
        return recipientId;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getChatId() {
        return chatId;
    }

    public String getId() {
        return id;
    }

    // Builder class
    public static class Builder {
        private String id;
        private String chatId;
        private String senderId;
        private String recipientId;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder chatId(String chatId) {
            this.chatId = chatId;
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

        public ChatRoom build() {
            return new ChatRoom(this);
        }
    }

}