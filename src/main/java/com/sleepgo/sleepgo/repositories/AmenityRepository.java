package com.sleepgo.sleepgo.repositories;

import com.sleepgo.sleepgo.models.AmenitiesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface AmenityRepository extends JpaRepository<AmenitiesModel, Integer> {
    List<AmenitiesModel> findByHotelId (int amenityId);
    Optional<AmenitiesModel> findByAmenityId(int amenityId);

    List<AmenitiesModel> getAmenitiesByAmenityId(int amenityId);
}
