package com.robot.hotel.rest;

import com.robot.hotel.domain.Room;
import com.robot.hotel.dto.RoomDto;
import com.robot.hotel.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;


    @GetMapping("/rooms/all")
    public ResponseEntity<List<RoomDto>> findAll() {
        return ResponseEntity.ok(roomService.findAll());
    }


    @GetMapping("/room/number/{number}")
    public ResponseEntity<RoomDto> findByNumber(@PathVariable String number) {
        try {
            return ResponseEntity.ok(roomService.findByNumberRoomDto(number));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/rooms/free/checkIn/{checkIn}/checkOut/{checkOut}")
    public ResponseEntity<List<RoomDto>> findFreeInRange(@PathVariable LocalDate checkIn, @PathVariable LocalDate checkOut){
        return ResponseEntity.ok(roomService.findFreeRoomInDateRange(checkIn,checkOut));
    }

    @PostMapping("/room/save")
    public ResponseEntity<Void> save(@RequestBody Room room) {
            roomService.save(room);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/room/delete/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roomService.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


    @PutMapping("/room/update/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody RoomDto roomDto) {

        Room roomOldData = roomService.findById(id);

        if (roomOldData == null) {
            return ResponseEntity.notFound().build();
        } else {
            roomOldData.setNumber(roomDto.getNumber());
            roomOldData.setMaxGuests(roomDto.getMaxGuests());
            roomService.save(roomOldData);
        }
        return ResponseEntity.ok().build();
    }



}
