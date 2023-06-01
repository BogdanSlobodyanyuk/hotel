package com.robot.hotel.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class RoomDto {
    private String number;
    private Integer maxGuests;
    private List<Long> reservations;
}
