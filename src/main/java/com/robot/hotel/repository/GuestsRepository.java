package com.robot.hotel.repository;

import com.robot.hotel.domain.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface GuestsRepository extends JpaRepository<Guest, Integer> {

    Optional<Guest> findGuestByFirstName (String firstName);

    Optional<Guest> findGuestByFirstNameAndPassportNumber (String secondName, Integer passportNumber);

}
