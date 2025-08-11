package com.drivz.socketServer.dtos;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChatResponse {
    private String name;
    private String message;
    private String timeStamp;
}
