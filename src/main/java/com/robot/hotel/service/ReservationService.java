package com.robot.hotel.service;


import com.robot.hotel.domain.Guest;
import com.robot.hotel.domain.Reservation;
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
    private final RoomService roomService;
    private final GuestService guestService;


    public void save(Reservation reservation) {

        if (isReservationExisted(reservation)) {
            throw new IllegalArgumentException("Reservation isn't allowed, room has already been reserved");
        } else {
            reservationRepository.save(reservation);
        }
    }

    public void deleteById(Long id) {
        reservationRepository.deleteById(id);}



    public ReservationDto findByIdDto(Long id){

        return buidReservationDto(reservationRepository.findById(id).get());
    }

    public Reservation findByIdDomain (Long id){

        return reservationRepository.findById(id).get();
    }


    public void update(Long id, ReservationDto reservationDto) {

        Reservation reservationOldData = findByIdDomain(id);

        reservationOldData.setRoom(roomService.findByNumberRoomDomain(reservationDto.getRoom()));

        List<Guest> guests = null;

        for (int i = 0; i < reservationOldData.getGuests().size(); i++){
            guests.add(guestService.findByPassportNumberDomain(reservationDto.getGuests().get(i)));
        }

        findByIdDomain(id).setGuests(guests);

        save(reservationOldData);

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
