package com.robot.hotel.repository;

import com.robot.hotel.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {


    @Query("SELECT r  FROM Room r WHERE r.id NOT IN " +
            "(SELECT rv.roomId.id FROM Reservation rv" +
            " WHERE rv.checkIn <= :checkOut AND rv.checkOut >= :checkIn)")



    Optional<Room> findRoomsNotReservationListInRange(
            @Param("checkIn") LocalDate checkIn,
            @Param("checkOut") LocalDate checkOut);

}
