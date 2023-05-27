package com.robot.hotel.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class RoomDto {

    Integer roomId;
    String roomName;
    List<String> guestsName;
    Integer price;

}
