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
            return ResponseEntity.ok(roomService.findByNumber(number));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/rooms/free/checkIn/{checkIn}/checkOut/{checkOut}")
    public ResponseEntity<List<RoomDto>> findFreeInRange(@PathVariable LocalDate checkIn, @PathVariable LocalDate checkOut){
        return ResponseEntity.ok(roomService.findFreeRoomInDateRange(checkIn,checkOut));
    }

    @PostMapping("/room/save")
    public ResponseEntity<Void> save(@RequestBody RoomDto roomDto) {

        Room room = Room.builder()
                .number(roomDto.getNumber())
                .maxGuests(roomDto.getMaxGuests())
                .build();

        String firstNumber = room.getNumber();
        RoomDto roomExample = roomService.findByNumber(firstNumber);
        String secondNumber = roomExample.getNumber();

        if (firstNumber.toLowerCase().equals(secondNumber.toLowerCase())) {
            throw new IllegalArgumentException("Room is already added !");
        } else {
            roomService.save(room);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping("/room/update/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody RoomDto roomDto) {

        Room room = roomService.findById(id);

        if (room == null) {
            return ResponseEntity.notFound().build();
        } else {
            room.setNumber(roomDto.getNumber());
            room.setMaxGuests(roomDto.getMaxGuests());
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/room/delete/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roomService.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


}
