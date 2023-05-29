package com.robot.hotel.service;


import com.robot.hotel.domain.Reservation;
import com.robot.hotel.domain.Room;
import com.robot.hotel.dto.ReservationDto;
import com.robot.hotel.repository.ReservationRepository;
import com.robot.hotel.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;


    public void save(Reservation reservation) {

        if (isReserveExists(reservation)) {
            throw new IllegalArgumentException("Reservation has already been already created");
        } else {
            reservationRepository.save(reservation);
        }
    }

    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }


    public List<ReservationDto> findAll() {

        return reservationRepository.findAll()
                .stream()
                .map(ReservationService::buidReservationDto)
                .collect(Collectors.toList());
    }




    public void changeRoom(Long roomId, Long reservationId) {

        Reservation reservation = reservationRepository.findById(reservationId).get();
        Room room = roomRepository.findById(roomId).get();
        reservation.setRoomId(room);

        if (isReserveExists(reservation)) {
            throw new IllegalArgumentException("Reservation has already been created");

        } else {
            reservationRepository.save(reservation);
        }
    }


    private boolean isReserveExists(Reservation reservation) {
        return reservationRepository.existsByRoomIdAndCheckInAndCheckOut(
                reservation.getRoomId(), reservation.getCheckIn(), reservation.getCheckOut());
    }


    private static ReservationDto buidReservationDto(Reservation reservation) {

        return ReservationDto.builder()
                .reservationID(reservation.getId())
                .room(reservation.getRoomId().getNumber())
                .guest(reservation.getGuestId().getName())
                .checkIn(reservation.getCheckIn())
                .checkOut(reservation.getCheckOut())
                .build();

    }


}
