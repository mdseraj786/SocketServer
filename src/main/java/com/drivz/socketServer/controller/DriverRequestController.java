package com.drivz.socketServer.controller;

import com.drivz.commonLibrary.models.BookingStatus;
import com.drivz.socketServer.dtos.RideRequestDto;
import com.drivz.socketServer.dtos.RideResponseDto;
import com.drivz.socketServer.dtos.UpdateBookingRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/socket")
public class DriverRequestController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final RestTemplate restTemplate;
    public DriverRequestController(SimpMessagingTemplate simpMessagingTemplate, RestTemplate restTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.restTemplate = restTemplate;
    }
    
    @PostMapping("/newride")
    public ResponseEntity<Boolean> raiseRideRequest(@RequestBody RideRequestDto requestDto){
        sendDriversNewRideRequest(requestDto);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }
    
    public void sendDriversNewRideRequest(RideRequestDto rideRequestDto){
//        return "Periodic message from server " + System.currentTimeMillis(); 
        // TODO: Ideally the request should only go to nearby drivers, but for simplicity we sent it every driver
        simpMessagingTemplate.convertAndSend("/topic/rideRequest",rideRequestDto);
    }
    
    // making synchronize for only call one driver at a time
    @MessageMapping("/rideResponse/{userId}")
    public synchronized void rideResponseHandler(@DestinationVariable("userId") String userId, RideResponseDto responseDto){
        // simply call update api for updating booking details
        UpdateBookingRequestDto requestDto = UpdateBookingRequestDto.builder()
                .status(BookingStatus.SCHEDULED)
                .driverId(Long.parseLong(userId))
                
                .build();
        ResponseEntity<UpdateBookingRequestDto> response =restTemplate.postForEntity("http://localhost:8485/api/v1/booking/"+responseDto.getBookingId(),requestDto, UpdateBookingRequestDto.class);
       
        System.out.println(response);
    }

}