package com.robot.hotel.service;


import com.robot.hotel.domain.Guest;
import com.robot.hotel.domain.Reservation;
import com.robot.hotel.dto.GuestDto;
import com.robot.hotel.repository.GuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GuestService {

    private final GuestRepository guestRepository;


    public void save(Guest guest) {
        guestRepository.save(guest);
    }

    public void deleteById(Long id) { guestRepository.deleteById(id);}

    public List<com.robot.hotel.dto.GuestDto> findAll() {
        return guestRepository.findAll().stream()
                .map(GuestService::buildGuestDto)
                .collect(Collectors.toList());
    }


    public Guest findById(Long id) {
        return guestRepository.findById(id).get();
    }


    public com.robot.hotel.dto.GuestDto findBySureNameDto(String sureName) {
        return buildGuestDto(guestRepository.findBySureName(sureName));
    }



    public GuestDto findByPassportNumberDto(String passportNumber) {
        return buildGuestDto(guestRepository.findByPassportNumber(passportNumber));
    }

    public Guest findByPassportNumberDomain (String passportNumber){
        return guestRepository.findByPassportNumber(passportNumber);
    }


    public static com.robot.hotel.dto.GuestDto buildGuestDto(Guest guest) {

        return com.robot.hotel.dto.GuestDto.builder()
                .id(guest.getId())
                .name(guest.getName())
                .sureName(guest.getSureName())
                .passportNumber(guest.getPassportNumber())
                .phoneNumber(guest.getPhoneNumber())
                .reservations(guest.getReservations()
                        .stream()
                        .map(Reservation::getId)
                        .collect(Collectors.toList()))
                .build();
    }


}
