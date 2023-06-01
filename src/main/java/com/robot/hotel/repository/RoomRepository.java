package com.robot.hotel.repository;

import com.robot.hotel.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;


@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Room findRoomByNumber (String number);
    List<Room> findAllByReservationsCheckInAfterAndReservationsCheckOutBefore(LocalDate checkIn, LocalDate checkOut);

}
