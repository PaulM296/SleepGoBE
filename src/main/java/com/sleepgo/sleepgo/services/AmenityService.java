package com.sleepgo.sleepgo.services;

import com.sleepgo.sleepgo.exceptions.AmenityAlreadyExistsException;
import com.sleepgo.sleepgo.exceptions.AmenityNotFoundException;
import com.sleepgo.sleepgo.exceptions.HotelNotFoundException;
import com.sleepgo.sleepgo.models.AmenitiesModel;
import com.sleepgo.sleepgo.models.RoomModel;
import com.sleepgo.sleepgo.repositories.AmenityRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class AmenityService {
    @Resource
    private AmenityRepository amenityRepository;
    public List<AmenitiesModel> getAllAmenities() {
        return amenityRepository.findAll();
    }

    public AmenitiesModel getAmenityById(int amenityId) throws AmenityNotFoundException {
        Optional<AmenitiesModel> optionalAmenity = amenityRepository.findByAmenityId(amenityId);
        if (optionalAmenity.isPresent()) {
            return optionalAmenity.get();
        } else {
            throw new AmenityNotFoundException(String.format("Amenity with ID %d not found", amenityId));
        }
    }

    public AmenitiesModel saveAmenity(AmenitiesModel amenity) {
        return amenityRepository.save(amenity);
    }

    public List<AmenitiesModel> getAmenitiesByHotelId(int hotelId) {
        return amenityRepository.findByHotelId(hotelId);
    }

    public void deleteAmenity(int amenityId) throws AmenityNotFoundException {
        amenityRepository.deleteById(amenityId);
    }

    public void deleteAmenityByHotel(int hotelId) throws HotelNotFoundException {
        amenityRepository.deleteByHotelId(hotelId);
    }
}
