package com.sleepgo.sleepgo.controllers;

import com.sleepgo.sleepgo.exceptions.HotelNotFoundException;
import com.sleepgo.sleepgo.models.HotelModel;
import com.sleepgo.sleepgo.services.HotelService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Resource
    private HotelService hotelService;

    @PutMapping("/{id}")
    public HotelModel updateHotel(@PathVariable("id") int hotelId, @RequestBody HotelModel updatedHotel) throws HotelNotFoundException {
        HotelModel hotel = hotelService.updateHotel(hotelId, updatedHotel);
        return hotel;
    }

    @GetMapping("/{id}")
    public HotelModel getHotelById(@PathVariable("id") int hotelId) throws HotelNotFoundException {
        HotelModel hotel = hotelService.getHotelById(hotelId);
        return hotel;
    }

    @GetMapping("")
    public List<HotelModel> getAllHotels() {
        List<HotelModel> hotels = hotelService.getAllHotels();
        return hotels;
    }

    @DeleteMapping("/{id}")
    public void deleteHotelById(@PathVariable("id") int hotelId) throws HotelNotFoundException {
        hotelService.deleteHotelById(hotelId);
    }
}
