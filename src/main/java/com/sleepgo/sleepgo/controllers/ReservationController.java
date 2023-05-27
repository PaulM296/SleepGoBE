package com.sleepgo.sleepgo.controllers;

import com.sleepgo.sleepgo.exceptions.ReservationNotFoundException;
import com.sleepgo.sleepgo.models.ReservationModel;
import com.sleepgo.sleepgo.repositories.ReservationRepository;
import com.sleepgo.sleepgo.services.ReservationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    @Resource
    private ReservationService reservationService;
    @PostMapping
    public ReservationModel createReservation(@RequestBody ReservationModel reservation) {
        return reservationService.createReservation(reservation);
    }

    @GetMapping
    public List<ReservationModel> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public ReservationModel getReservationById(@PathVariable(value = "id") int id) throws ReservationNotFoundException {
        return reservationService.getReservationById(id);
    }

    @PutMapping("/{id}")
    public ReservationModel updateReservation(@PathVariable(value = "id") int id, @RequestBody ReservationModel reservationDetails) throws ReservationNotFoundException {
        return reservationService.updateReservation(id, reservationDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable(value = "id") int id) throws ReservationNotFoundException {
        reservationService.deleteReservation(id);
    }

}
