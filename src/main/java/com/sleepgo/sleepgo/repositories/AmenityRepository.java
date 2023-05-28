package com.sleepgo.sleepgo.repositories;

import com.sleepgo.sleepgo.models.AmenitiesModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AmenityRepository extends JpaRepository<AmenitiesModel, Integer> {
    List<AmenitiesModel> findByHotelId (int amenityId);
    Optional<AmenitiesModel> findByAmenityId(int amenityId);
}
