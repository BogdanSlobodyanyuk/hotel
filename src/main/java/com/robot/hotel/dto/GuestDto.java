package com.robot.hotel.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GuestDto {

    private Long id;
    private String name;
    private String sureName;
    private String passportNumber;
    private String phoneNumber;
    private List<Long> reservations;
}
