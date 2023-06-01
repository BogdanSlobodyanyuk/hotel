package com.robot.hotel.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import java.time.LocalDate;
import java.util.List;

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EnableScheduling
@EnableTransactionManagement
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long id;
    @ManyToMany(mappedBy = "reservations")
    private List <Guest> guests;

    @ManyToOne
    @JoinColumn(name = "Room")
    private Room room;

    @Column(name = "Check_in")
    @Temporal(TemporalType.DATE)
    private LocalDate checkIn;

    @Column(name = "Check_out")
    @Temporal(TemporalType.DATE)
    private LocalDate checkOut;


}
