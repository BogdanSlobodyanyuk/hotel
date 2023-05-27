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


    @GetMapping("/guest/all")
    public ResponseEntity<List<GuestDto>> findAll() {
        return ResponseEntity.ok(guestService.findAllGuests());
    }

    @GetMapping("/guest/id/{id}")
    public ResponseEntity<Guest> findGuestById(@PathVariable Integer id) {
        return guestService.findGuestForId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/guest/firstName/{firstName}")
    public ResponseEntity<Guest> findGuestByName(@PathVariable String firstName) {
        return guestService.findGuestForName(firstName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/guest/findnamenumber")
    public ResponseEntity<Guest> findGuestByName(@RequestParam String secondName, Integer passportNumber) {
        return guestService.findGuestForNameAndPassport(secondName,passportNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/guest/add")
    public ResponseEntity<Void> saveGuest(@RequestBody Guest guest) {
        guestService.saveGuest(guest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/guest/{id}/rooms/{roomId}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @PathVariable Integer roomId){
        guestService.addRoom(id,roomId);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
