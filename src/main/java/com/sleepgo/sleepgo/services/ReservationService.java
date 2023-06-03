package com.sleepgo.sleepgo.services;

import com.sleepgo.sleepgo.exceptions.ReservationNotFoundException;
import com.sleepgo.sleepgo.exceptions.RoomNotFoundException;
import com.sleepgo.sleepgo.exceptions.UserNotFoundException;
import com.sleepgo.sleepgo.models.ReservationModel;
import com.sleepgo.sleepgo.models.ReviewModel;
import com.sleepgo.sleepgo.models.UserModel;
import com.sleepgo.sleepgo.repositories.ReservationRepository;
import com.sleepgo.sleepgo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Resource
    private ReservationRepository reservationRepository;

    @Resource
    private UserRepository userRepository;

    public ReservationModel createReservation(ReservationModel reservation) {
        return reservationRepository.save(reservation);
    }

    public List<ReservationModel> getAllReservations() {
        return reservationRepository.findAll();
    }

    public ReservationModel getReservationById(int reservationId) throws ReservationNotFoundException {
        Optional<ReservationModel> optionalReservation = reservationRepository.findByReservationId(reservationId);
        if (optionalReservation.isPresent()) {
            return optionalReservation.get();
        } else {
            throw new ReservationNotFoundException(String.format("The reservation with ID %d not found", reservationId));
        }
    }

    public ReservationModel updateReservation(int reservationId, ReservationModel reservationDetails) throws ReservationNotFoundException {
        ReservationModel reservation = getReservationById(reservationId);

        reservation.setUserId(reservationDetails.getUserId());
        reservation.setCheckInDate(reservationDetails.getCheckInDate());
        reservation.setCheckOutDate(reservationDetails.getCheckOutDate());
        reservation.setPrice(reservationDetails.getPrice());
        reservation.setStatus(reservationDetails.getStatus());

        return reservationRepository.save(reservation);
    }

    public void deleteReservation(int reservationId) throws ReservationNotFoundException{
        ReservationModel reservation = getReservationById(reservationId);
        reservationRepository.delete(reservation);
    }

    public void deleteReservationsByUserId(int userId) {
        reservationRepository.deleteByUserId(userId);
    }

    public List<ReservationModel> getReservationByUsername(String username) throws UserNotFoundException {
        List<UserModel> users = userRepository.findByUsername(username);
        if (users.isEmpty()) {
            throw new UserNotFoundException(String.format("The user with the %s username does not exist", username));
        }
        int userId = users.get(0).getId();
        return reservationRepository.findByUserId(userId);
    }
}
