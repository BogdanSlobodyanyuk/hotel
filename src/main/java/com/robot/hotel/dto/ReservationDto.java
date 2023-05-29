package com.robot.hotel.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Data
@Builder
public class ReservationDto {


    private Long reservationID;
    private String guest;
    private String room;
    private LocalDate checkIn;
    private LocalDate checkOut;


}
