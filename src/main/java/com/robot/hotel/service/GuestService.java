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

    public List<GuestDto> findAll() {
        return guestRepository.findAll().stream()
                .map(GuestService::buildGuestDto)
                .collect(Collectors.toList());
    }


    public Guest findById(Long id) {
        return guestRepository.findById(id).get();
    }


    public GuestDto findBySureName(String sureName) {
        return buildGuestDto(guestRepository.findBySureName(sureName));
    }


    public GuestDto findByPassportNumber(String passportNumber) {
        return buildGuestDto(guestRepository.findByPassportNumber(passportNumber));
    }


    public static GuestDto buildGuestDto(Guest guest) {

        return GuestDto.builder()
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
