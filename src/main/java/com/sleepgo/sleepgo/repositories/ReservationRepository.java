package com.sleepgo.sleepgo.repositories;

import com.sleepgo.sleepgo.models.ReservationModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationsRepository extends JpaRepository<ReservationModel, Integer> {
    List<ReservationModel> findByReservationId(int reservationId);
    List<ReservationModel> findByUserId(int userId);
}
