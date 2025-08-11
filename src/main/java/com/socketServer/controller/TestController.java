package com.drivz.socketServer.controller;

import com.drivz.socketServer.dtos.ChatRequest;
import com.drivz.socketServer.dtos.ChatResponse;
import com.drivz.socketServer.dtos.TestRequestDto;
import com.drivz.socketServer.dtos.TestResponseDto;
import org.springframework.messaging.converter.SimpleMessageConverter;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TestController {
    
    private final SimpMessagingTemplate simpMessagingTemplate;

    public TestController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/ping")
    @SendTo("/topic/ping")
    public TestResponseDto pingCheck(TestRequestDto dto){
        System.out.println("Received message from client: "+ dto.getData());
        
        return new TestResponseDto("Received");
    }
    
//    @SendTo("/topic/scheduled")
//    @Scheduled(fixedDelay = 2000)
//    public void sendPeriodicMessage(){
////        return "Periodic message from server " + System.currentTimeMillis(); 
//        simpMessagingTemplate.convertAndSend("/topic/scheduled","Periodic message from server" + System.currentTimeMillis());
//    }
    
    @MessageMapping("/chat/{room}")
    @SendTo("/topic/message/{room}")// for custom room chat -- it sends response only this room
    public ChatResponse chatMessage(@DestinationVariable String room, ChatRequest chatRequest){
        return ChatResponse.builder()
                .name(chatRequest.getName())
                .message(chatRequest.getMessage())
                .timeStamp(""+ System.currentTimeMillis()  )
                .build();
    }
    @MessageMapping("/chat/{room}/{userId}")  // for custom room chat -- it sends response only this room
//    @SendTo("/topic/message/{room}")
    public void privateChatMessage(@DestinationVariable String room, @DestinationVariable String userId, ChatRequest chatRequest){
        ChatResponse response = ChatResponse.builder()
                .name(chatRequest.getName())
                .message(chatRequest.getMessage())
                .timeStamp(""+ System.currentTimeMillis()  )
                .build();
        
        simpMessagingTemplate.convertAndSendToUser(userId,"/queue/privateChat/" + room, response);
    }
}
