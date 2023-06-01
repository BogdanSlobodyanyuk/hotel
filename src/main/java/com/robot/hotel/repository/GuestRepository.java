package com.robot.hotel.repository;

import com.robot.hotel.domain.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

   Guest findBySureName (String sureName);
   Guest findByPassportNumber (String passportNumber);






}
