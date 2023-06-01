package com.robot.hotel.repository;

import com.robot.hotel.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    boolean existsByRoomNumberAndCheckInBeforeAndCheckOutAfter(String roomNumber, LocalDate checkOut, LocalDate checkIn);

}