package com.drivz.socketServer.dtos;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChatRequest {
    private String name;
    private String message;
}
