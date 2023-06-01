package com.robot.hotel.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Sure_name")
    private String sureName;
    @Column(name = "Passport_identification", unique = true)
    private String passportNumber;
    @Column(name = "Phone_number")
    private String phoneNumber;
    @ManyToMany
    @JoinTable(name = "Guest_reservation",
    joinColumns = {@JoinColumn(name = "Guest_id")},
    inverseJoinColumns = {@JoinColumn(name = "Reservation_id")})
    private List<Reservation> reservations;

}
