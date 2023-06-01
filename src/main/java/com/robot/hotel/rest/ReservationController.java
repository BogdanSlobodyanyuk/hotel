package com.robot.hotel.rest;


import com.robot.hotel.domain.Reservation;
import com.robot.hotel.dto.ReservationDto;
import com.robot.hotel.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReservationController {


    private final ReservationService reservationService;

    @GetMapping("/reservations/id/{id}")
    public ResponseEntity<ReservationDto> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(reservationService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/reservation/save")
    public ResponseEntity<Void> save (@RequestBody Reservation reservation){
        reservationService.save(reservation);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping("/reservation/id/{id}/roomNumber/{roomNumber}")
    public ResponseEntity<Void> update(@PathVariable String roomNumber, @PathVariable Long id) {
        reservationService.changeRoom(roomNumber, id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


    @DeleteMapping("")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        reservationService.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


}

