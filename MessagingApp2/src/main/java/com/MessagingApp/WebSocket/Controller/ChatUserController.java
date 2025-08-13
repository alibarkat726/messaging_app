package com.MessagingApp.WebSocket.Controller;


import com.MessagingApp.WebSocket.Model.ChatUser;
import com.MessagingApp.WebSocket.Services.ChatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChatUserController {
    @Autowired
    ChatUserService userService;
    @MessageMapping("/user.addUser")
    @SendTo("/topic/users")
    public ChatUser addUser(@Payload ChatUser user){
        userService.addUser(user);
        return user;
    }

//    @MessageMapping("user.disconnect")
//    @SendTo("/user/topic")
//    public ChatUser disconnect(@Payload ChatUser user){
//        userService.disconnectUser(user);
//        return user;
//    }
    @GetMapping("/users")
    public ResponseEntity<List<ChatUser>> connectedUsers(){
        return userService.getConnectedUsers();
    }
}