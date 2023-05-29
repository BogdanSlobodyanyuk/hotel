package com.robot.hotel.domain;


import jakarta.persistence.*;
import lombok.*;

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column
    String firstName;

    @Column
    String secondName;

    @Column
    Integer passportNumber;

    @ManyToOne
    @JoinColumn (name = "guest_group_id")
    Room roomNumber;



}