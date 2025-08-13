package com.MessagingApp.WebSocket.Controller;
import com.MessagingApp.WebSocket.Model.ChatMessage;
import com.MessagingApp.WebSocket.Model.ChatNotification;
import com.MessagingApp.WebSocket.Services.ChatMessageService;
import com.MessagingApp.WebSocket.Services.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ChatMessageController {

    @Autowired
    ChatRoomService chatRoomService;

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    ChatMessageService messageService;

    public ChatMessageController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat")
    public void sendMessage (@Payload ChatMessage chatMessage){
        ChatMessage savedMes=messageService.save(chatMessage);
        //jhon/queue/messages jhon is subscribing to this queue
        messagingTemplate.convertAndSendToUser(chatMessage.getRecipientId(),"queue/messages",
                ChatNotification.builder()
                        .id(savedMes.getId())
                        .recipientId(savedMes.getRecipientId())
                        .senderId(savedMes.getSenderId())
                        .content(savedMes.getContent())
                        .build()
        );
    }
    @GetMapping("/message/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>>  getChatMessages(
            @PathVariable("senderId") String senderId,
            @PathVariable("recipientId") String recipientId
    )
    {
        return ResponseEntity.ok(messageService.findChatMessages(senderId,recipientId));
    }
}
