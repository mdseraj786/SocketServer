package com.drivz.socketServer.dtos;

import com.drivz.socketServer.models.ExactLocation;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RideRequestDto {
    private Long passengerId;
    
    private ExactLocation startLocation;
    
    private ExactLocation endLocation;
    
    private List<Long> driverId;
    
    private long bookingId;
}
