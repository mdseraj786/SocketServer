package com.drivz.socketServer.consumer;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerServiceNew {
    
    @KafkaListener(topics = "sample-topic",groupId = "sample-group-3")
    public void listen(String message){
        System.out.println("Kafka message from new group-3 simple topic: " + message);
    }
}
