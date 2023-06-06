package com.sleepgo.sleepgo.controllers;

import com.sleepgo.sleepgo.exceptions.AmenityAlreadyExistsException;
import com.sleepgo.sleepgo.exceptions.AmenityNotFoundException;
import com.sleepgo.sleepgo.models.AmenitiesModel;
import com.sleepgo.sleepgo.models.RoomModel;
import com.sleepgo.sleepgo.services.AmenityService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/amenities")
@CrossOrigin(origins = "*")
public class AmenityController {
    @Resource
    private AmenityService amenityService;
    public AmenityController(AmenityService amenityService) {
        this.amenityService = amenityService;
    }

    @GetMapping
    public List<AmenitiesModel> getAllAmenities() {
        return amenityService.getAllAmenities();
    }

    @GetMapping("/{amenityId}")
    public AmenitiesModel getAmenityById(@PathVariable int amenityId) throws AmenityNotFoundException {
        return amenityService.getAmenityById(amenityId);
    }

    @PostMapping
    public AmenitiesModel createAmenity(@RequestBody AmenitiesModel amenity) {
        return amenityService.saveAmenity(amenity);
    }

    @DeleteMapping("/{amenityId}")
    public void deleteAmenity(@PathVariable int amenityId) throws AmenityNotFoundException{
        amenityService.deleteAmenity(amenityId);
    }

    @GetMapping("/getByHotelId")
    public List<AmenitiesModel> getAmenitiesByHotelId(@RequestParam int hotelId) {
        return amenityService.getAmenitiesByHotelId(hotelId);
    }
}
