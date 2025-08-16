package com.drivz.socketServer.dtos;

import com.drivz.commonLibrary.models.BookingStatus;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookingRequestDto {
    private BookingStatus status;
    private Long driverId;  
}
