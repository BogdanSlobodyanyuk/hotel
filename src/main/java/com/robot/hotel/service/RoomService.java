package com.robot.hotel.service;

import com.robot.hotel.domain.Reservation;
import com.robot.hotel.domain.Room;
import com.robot.hotel.dto.RoomDto;
import com.robot.hotel.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor

public class RoomService {

    private final RoomRepository roomRepository;


    public void save(Room room) {
        roomRepository.save(room);
    }

    public void deleteById(Long id) {
        roomRepository.deleteById(id);
    }

    public Room findById(Long id) {

        return roomRepository.findById(id).get();
    }

    public List<RoomDto> findAll() {
        return roomRepository.findAll().stream()
                .map(RoomService::buildRoomDto)
                .collect(Collectors.toList());
    }

    public List<RoomDto> findFreeRoomInDateRange(LocalDate checkIn, LocalDate checkOut) {

        List<Room> availableRooms = roomRepository.findAllByReservationsCheckInAfterAndReservationsCheckOutBefore(checkIn,checkOut);
        List<RoomDto> availableRoomsDto = new ArrayList<>();

        for (Room room : availableRooms) {
            RoomDto roomDto = RoomDto.builder()
                    .number(room.getNumber())
                    .maxGuests(room.getMaxGuests())
                    .build();
            availableRoomsDto.add(roomDto);
        }
        return availableRoomsDto;
    }

    public RoomDto findByNumber(String number) {
        return buildRoomDto(roomRepository.findRoomByNumber(number));
    }


    private static RoomDto buildRoomDto(Room room) {
        return RoomDto.builder()
                .number(room.getNumber())
                .maxGuests(room.getMaxGuests())
                .reservations(room.getReservations()
                        .stream()
                        .map(Reservation::getId)
                        .collect(Collectors.toList()))
                .build();
    }
}
