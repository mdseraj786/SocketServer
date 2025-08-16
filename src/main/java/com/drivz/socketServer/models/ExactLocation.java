package com.drivz.socketServer.models;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExactLocation {

    private Double latitude;
    private Double longitude;

}
