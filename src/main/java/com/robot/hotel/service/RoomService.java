package com.robot.hotel.service;

import com.robot.hotel.domain.Room;
import com.robot.hotel.dto.RoomDto;
import com.robot.hotel.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

    public List<RoomDto> findAll() {
        return roomRepository.findAll().stream()
                .map(RoomService::buildRoomDto)
                .collect(Collectors.toList());
    }


    private static RoomDto buildRoomDto(Room room) {
        return RoomDto.builder()
                .number(room.getNumber())
                .maxGuests(room.getMaxGuests())
                .build();
    }
}
