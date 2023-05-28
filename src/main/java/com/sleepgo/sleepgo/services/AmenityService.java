package com.sleepgo.sleepgo.services;

import com.sleepgo.sleepgo.exceptions.AmenityNotFoundException;
import com.sleepgo.sleepgo.models.AmenitiesModel;
import com.sleepgo.sleepgo.repositories.AmenityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AmenityService {

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

    public void deleteAmenity(int amenityId) {
        amenityRepository.deleteById(amenityId);
    }
}
