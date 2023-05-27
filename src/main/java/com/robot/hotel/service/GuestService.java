package com.robot.hotel.service;


import com.robot.hotel.domain.Guest;
import com.robot.hotel.dto.GuestDto;
import com.robot.hotel.repository.GuestsRepository;
import com.robot.hotel.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GuestService {


    private final GuestsRepository guestsRepository;
    private final RoomRepository roomRepository;

    public List<GuestDto> findAllGuests() {
        return guestsRepository.findAll().stream()
                .map(GuestService::buildGuestsDto)
                .collect(Collectors.toList());
    }

    private static GuestDto buildGuestsDto(Guest guest) {
        String group = "Guest doesn't have a room";
        if (guest.getRoomNumber() != null) {
            group = guest.getRoomNumber().getRoomId().toString();
        }
        return GuestDto.builder()
                .id(guest.getId())
                .firstName(guest.getFirstName())
                .secondName(guest.getSecondName())
                .roomNumber(group)
                .passportNumber(guest.getPassportNumber())
                .build();
    }

    public Optional<Guest> findGuestForId(Integer id) {
        return guestsRepository.findById(id);
    }

    public Optional<Guest> findGuestForName(String name) {
        return guestsRepository.findGuestByFirstName(name);
    }

    public Optional<Guest> findGuestForNameAndPassport(String firstName, Integer passportNumber) {
        return guestsRepository.findGuestByFirstNameAndPassportNumber(firstName, passportNumber);
    }

    public void addRoom(Integer guestID, Integer roomId) {
        var room = roomRepository.findById(roomId).get();
        var guest = guestsRepository.findById(guestID).get();
        guest.setRoomNumber(room);
        guestsRepository.save(guest);
    }


    public void saveGuest(Guest guest) {
        guestsRepository.save(guest);
    }
}
