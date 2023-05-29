package com.robot.hotel.repository;

import com.robot.hotel.domain.Reservation;
import com.robot.hotel.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT CASE WHEN COUNT(rs) > 0 THEN true ELSE false END FROM Reservation rs " +
            "WHERE rs.roomId = :roomId " +
            "AND rs.checkIn = :checkIn " +
            "AND rs.checkOut = :checkOut")


    boolean existsByRoomIdAndCheckInAndCheckOut(
            @Param("roomId") Room roomId,
            @Param("checkIn") LocalDate checkIn,
            @Param("checkOut") LocalDate checkOut);


}
