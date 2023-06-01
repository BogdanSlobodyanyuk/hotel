package com.robot.hotel.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data
@Builder
public class ReservationDto {

    private List<String> guests;
    private String room;
    private LocalDate checkIn;
    private LocalDate checkOut;


}
