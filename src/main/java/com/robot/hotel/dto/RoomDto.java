package com.robot.hotel.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class RoomDto {

    private String number;
    private Integer maxGuests;
}
