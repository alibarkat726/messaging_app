package com.MessagingApp.WebSocket.Services;

import com.MessagingApp.WebSocket.Model.ChatUser;
import com.MessagingApp.WebSocket.Repository.ChatUserRepo;
import com.MessagingApp.WebSocket.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class  ChatUserService {

    @Autowired
    ChatUserRepo chatUserRepo;

//    public void disconnectUser(ChatUser users){
//        ChatUser users1 = chatUserRepo.findById(users.getId());
//        if ( users1 != null){
//            users1.setStatus(Status.OFFLINE);
//            chatUserRepo.save(users1);
//        }
//    }
    public ResponseEntity<List<ChatUser>> getConnectedUsers(){
        List<ChatUser> user = chatUserRepo.findAllByStatus(Status.ONLINE);
        if (!user.isEmpty()){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    public ChatUser addUser(ChatUser user) {
        user.setStatus(Status.ONLINE);
        return chatUserRepo.save(user);
    }
}
