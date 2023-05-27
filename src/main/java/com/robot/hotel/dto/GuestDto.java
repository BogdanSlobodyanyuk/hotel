package com.robot.hotel.dto;

import lombok.Builder;
import lombok.Data;



@Data
@Builder

public class GuestDto {
    Integer id;
    String firstName;
    String secondName;
    String roomNumber;
    Integer passportNumber;


}
