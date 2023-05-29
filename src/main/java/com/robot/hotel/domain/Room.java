package com.robot.hotel.domain;

import jakarta.persistence.*;
import jakarta.validation.Constraint;
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
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer roomId;

    @Column(unique = true)
    String roomName;

    @Column
    Integer price;

    @OneToMany(mappedBy = "roomNumber")
    List<Guest> guests;



}
