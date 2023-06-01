package com.robot.hotel.rest;


import com.robot.hotel.domain.Guest;
import com.robot.hotel.dto.GuestDto;
import com.robot.hotel.service.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GuestController {

    private final GuestService guestService;


    @GetMapping("/guests/all")
    public ResponseEntity<List<GuestDto>> findAll() {
        return ResponseEntity.ok(guestService.findAll());
    }


    @GetMapping("/guest/sureName/{sureName}")
    public ResponseEntity<GuestDto> findBySureName(@PathVariable String sureName) {
        try {
            return ResponseEntity.ok(guestService.findBySureName(sureName));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/guest/passport/{passportNumber}")
    public ResponseEntity<GuestDto> findByPassportNumber(@PathVariable String passportNumber) {
        try {
            return ResponseEntity.ok(guestService.findByPassportNumber(passportNumber));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @PostMapping("/guest/save")
    public ResponseEntity<Void> saveGuest(@RequestBody Guest guest) {
//        Guest guest1 = guest;
//
//        String firstPassport = guest1.getPassportNumber();
//        GuestDto guestExample = guestService.findByPassportNumber(firstPassport);
//        String secondPassport = guestExample.getPassportNumber();
//
//        if (firstPassport.toLowerCase().equals(secondPassport.toLowerCase())) {
//            throw new IllegalArgumentException("Guest is already added !");
//        } else {
            guestService.save(guest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping("/guest/update/{id}")
    public ResponseEntity<Void> updateGuest(@PathVariable("id") Long id, @RequestBody Guest guest) {

        Guest guest1 = guestService.findById(id);

        if (guest1 == null) {
            return ResponseEntity.notFound().build();
        } else {
            guest1.setName(guest.getName());
            guest1.setSureName(guest.getName());
            guest1.setPhoneNumber(guest.getPhoneNumber());
            guest1.setPassportNumber(guest.getPassportNumber());
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/guest/delete/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        guestService.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


}
