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
    public ResponseEntity<List<GuestDto>> findAll() {return ResponseEntity.ok(guestService.findAll());}


    @GetMapping("/guest/sureName/{sureName}")
    public ResponseEntity<GuestDto> findBySureName(@PathVariable String sureName) {
        try {
            return ResponseEntity.ok(guestService.findBySureNameDto(sureName));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/guest/passport/{passportNumber}")
    public ResponseEntity<com.robot.hotel.dto.GuestDto> findByPassportNumber(@PathVariable String passportNumber) {
        try {
            return ResponseEntity.ok(guestService.findByPassportNumberDto(passportNumber));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @PostMapping("/guest/save")
    public ResponseEntity<Void> saveGuest(@RequestBody Guest guest) {
            guestService.save(guest);
            return ResponseEntity.status(HttpStatus.CREATED).build();

    }


    @PutMapping("/guest/update/{id}")
    public ResponseEntity<Void> updateGuest(@PathVariable Long id, @RequestBody Guest guest) {

        Guest guestOldData = guestService.findById(id);

        if (guestOldData == null) {
            return ResponseEntity.notFound().build();
        } else {
            guestOldData.setName(guest.getName());
            guestOldData.setSureName(guest.getSureName());
            guestOldData.setPhoneNumber(guest.getPhoneNumber());
            guestOldData.setPassportNumber(guest.getPassportNumber());
            guestService.save(guestOldData);
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/guest/delete/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        guestService.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


}
