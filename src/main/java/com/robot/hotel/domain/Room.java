package com.robot.hotel.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import java.util.List;

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long id;
    @Column(name = "Room_number", unique = true)
    private String number;
    @Column(name = "Max_guests")
    private Integer maxGuests;
    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations;


}
