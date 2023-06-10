package com.sleepgo.sleepgo.controllers;

import com.sleepgo.sleepgo.exceptions.InvalidTokenException;
import com.sleepgo.sleepgo.exceptions.ReservationNotFoundException;
import com.sleepgo.sleepgo.exceptions.UserNotFoundException;
import com.sleepgo.sleepgo.models.ReservationModel;
import com.sleepgo.sleepgo.models.ReviewModel;
import com.sleepgo.sleepgo.models.UserModel;
import com.sleepgo.sleepgo.repositories.ReservationRepository;
import com.sleepgo.sleepgo.services.AuthenticationService;
import com.sleepgo.sleepgo.services.ReservationService;
import com.sleepgo.sleepgo.services.UserService;
import org.apache.catalina.User;
import org.hibernate.SessionException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin(origins = "*")
public class ReservationController {
    @Resource
    private ReservationService reservationService;

    @Resource
    private AuthenticationService authenticationService;

    @Resource
    private UserService userService;
//    @PostMapping
//    public ReservationModel createReservation(@RequestBody ReservationModel reservation) {
//        return reservationService.createReservation(reservation);
//    }

    @PostMapping
    public ReservationModel createReservation(@RequestBody ReservationModel reservation, @RequestHeader("custom-token") String token) throws UserNotFoundException, InvalidTokenException {
        String username = authenticationService.getLoggedInUsername(token);
        UserModel user = userService.getByUsername(username);
        int userId = user.getId();

        reservation.setUserId(userId);

        return reservationService.createReservation(reservation);
    }

    @GetMapping
    public List<ReservationModel> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @PutMapping("/{id}")
    public ReservationModel updateReservation(@PathVariable(value = "id") int id, @RequestBody ReservationModel reservationDetails) throws ReservationNotFoundException {
        return reservationService.updateReservation(id, reservationDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable(value = "id") int id) throws ReservationNotFoundException {
        reservationService.deleteReservation(id);
    }

    @GetMapping("/{username}")
    public List<ReservationModel> getReservationsByUsername(@PathVariable String username, @RequestHeader("custom-token") String token) throws UserNotFoundException, InvalidTokenException {
        if (!authenticationService.checkAuthenticationSessionExists(username)) {
            throw new SessionException("Session does not exist");
        }
        if (authenticationService.checkAuthenticationToken(username, token)) {
            return reservationService.getReservationByUsername(username);
        } else {
            throw new InvalidTokenException("The token is invalid");
        }
    }

}
