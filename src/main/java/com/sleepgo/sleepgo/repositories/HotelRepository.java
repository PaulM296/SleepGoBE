package com.sleepgo.sleepgo.repositories;

import com.sleepgo.sleepgo.models.HotelModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<HotelModel, Integer> {
    List<HotelModel> findByHotelId(int hotelId);
    List<HotelModel> findByCountry(String country);
    List<HotelModel> findByHotelName(String name);
//    List<HotelModel> findByHotelAmenities(String name);
}
