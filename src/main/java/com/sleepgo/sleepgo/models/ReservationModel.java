package com.sleepgo.sleepgo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservations")
public class ReservationModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    @Id
    private int reservationId;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "check_in_date")
    private LocalDate checkInDate;
    @Column(name = "check_out_date")
    private LocalDate checkOutDate;
    private int price;
    private String status;
}
