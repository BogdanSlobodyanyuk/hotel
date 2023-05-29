package com.robot.hotel.rest;


import com.robot.hotel.domain.Room;
import com.robot.hotel.dto.RoomDto;
import com.robot.hotel.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoomController {



    private final RoomService roomService;


    @PostMapping("/room/add")
    public ResponseEntity<Void> saveRoom(@RequestBody Room room) {
        roomService.saveRoom(room);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/room/all")
    public List<RoomDto> findAll() {return roomService.findAll();}


    @PutMapping("/room/update")
    public ResponseEntity<Void> updateRoom(@RequestBody Room room) {
        roomService.saveRoom(room);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


    @DeleteMapping("/room/delete")
    public void deleteRoomById(@RequestBody Room room){
        roomService.deleteRoom(room);
    }



}
