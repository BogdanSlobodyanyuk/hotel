package com.robot.hotel.service;


import com.robot.hotel.domain.Guest;
import com.robot.hotel.domain.Reservation;
import com.robot.hotel.domain.Room;
import com.robot.hotel.dto.ReservationDto;
import com.robot.hotel.repository.ReservationRepository;
import com.robot.hotel.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;


    public void save(Reservation reservation) {

        if (isReservationExisted(reservation)) {
            throw new IllegalArgumentException("Reservation isn't allowed, room has already been reserved");
        } else {
            reservationRepository.save(reservation);
        }
    }

    public void deleteById(Long id) {
        reservationRepository.deleteById(id);}



    public ReservationDto findById(Long id){

        return buidReservationDto(reservationRepository.findById(id).get());
    }


    public void changeRoom(String roomNumber, Long id) {

        Reservation reservation = reservationRepository.findById(id).get();
        Room room = roomRepository.findRoomByNumber(roomNumber);
        reservation.setRoom(room);

        if (isReservationExisted(reservation)) {
            throw new IllegalArgumentException("Reservation isn't allowed, please choose another room");
        } else {
            reservationRepository.save(reservation);
        }
    }

    private boolean isReservationExisted(Reservation reservation) {
        return reservationRepository.existsByRoomNumberAndCheckInBeforeAndCheckOutAfter(
                reservation.getRoom().getNumber(), reservation.getCheckOut(), reservation.getCheckIn());
    }


    private static ReservationDto buidReservationDto(Reservation reservation) {
        return ReservationDto.builder()
                .room(reservation.getRoom().getNumber())
                .guests(reservation.getGuests()
                        .stream()
                        .map(Guest::getName)
                        .collect(Collectors.toList()))
                .checkIn(reservation.getCheckIn())
                .checkOut(reservation.getCheckOut())
                .build();

    }


}
