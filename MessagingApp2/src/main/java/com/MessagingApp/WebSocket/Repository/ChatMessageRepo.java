package com.MessagingApp.WebSocket.Repository;

import com.MessagingApp.WebSocket.Model.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ChatMessageRepo extends MongoRepository<ChatMessage, String> {
    List<ChatMessage> findByChatId(String s);

}



