package com.drivz.socketServer.dtos;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RideResponseDto {
    private boolean response;
    private long bookingId;
}
