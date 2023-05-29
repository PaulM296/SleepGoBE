package com.sleepgo.sleepgo.repositories;

import com.sleepgo.sleepgo.models.ReservationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ReservationRepository extends JpaRepository<ReservationModel, Integer> {
    Optional<ReservationModel> findByReservationId(int reservationId);
    List<ReservationModel> findByUserId(int userId);

    List<ReservationModel> deleteByUserId(int userId);
}
