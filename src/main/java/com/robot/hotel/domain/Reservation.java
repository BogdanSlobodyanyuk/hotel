package com.robot.hotel.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import java.time.LocalDate;

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
    @Column(name = "Id", nullable = false, unique = true)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "Guest_id")
    private Guest guestId;

    @ManyToOne
    @JoinColumn(name = "Room_id")
    private Room roomId;

    @Column(name = "Check_in")
    @Temporal(TemporalType.DATE)
    private LocalDate checkIn;

    @Column(name = "Check_out")
    @Temporal(TemporalType.DATE)
    private LocalDate checkOut;


}
