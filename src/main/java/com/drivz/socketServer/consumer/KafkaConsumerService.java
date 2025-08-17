package com.drivz.socketServer.consumer;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    
    @KafkaListener(topics = "sample-topic",groupId = "sample-group-1")
    public void listen(String message){
        System.out.println("Kafka message from simple topic: " + message);
    }
}
