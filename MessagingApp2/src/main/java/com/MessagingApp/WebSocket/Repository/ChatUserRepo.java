package com.MessagingApp.WebSocket.Repository;


import com.MessagingApp.WebSocket.Model.ChatUser;
import com.MessagingApp.WebSocket.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatUserRepo extends MongoRepository<ChatUser, String> {
    List<ChatUser> findAllByStatus(Status status);



}
