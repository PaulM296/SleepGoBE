package com.sleepgo.sleepgo.services;

import com.sleepgo.sleepgo.exceptions.HotelNotFoundException;
import com.sleepgo.sleepgo.models.HotelModel;
import com.sleepgo.sleepgo.repositories.HotelRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    @Resource
    private HotelRepository hotelRepository;

    public HotelModel saveHotel(HotelModel hotel) {
        return hotelRepository.save(hotel);
    }

    public HotelModel updateHotel(int hotelId, HotelModel updatedHotel) throws HotelNotFoundException {
        HotelModel hotel = getHotelById(hotelId);
        hotel.setHotelName(updatedHotel.getHotelName());
        hotel.setHotelDescription(updatedHotel.getHotelDescription());
        hotel.setPhoneNumber(updatedHotel.getPhoneNumber());
        hotel.setEmail(updatedHotel.getEmail());
        hotel.setCountry(updatedHotel.getCountry());
        hotel.setCity(updatedHotel.getCity());
        hotel.setAddress(updatedHotel.getAddress());
        hotel.setZipCode(updatedHotel.getZipCode());
        hotel.setLongitude(updatedHotel.getLongitude());
        hotel.setLatitude(updatedHotel.getLatitude());
        return hotelRepository.save(hotel);
    }

    public HotelModel getHotelById(int hotelId) throws HotelNotFoundException {
        Optional<HotelModel> optionalHotel = hotelRepository.findByHotelId(hotelId);
        if(optionalHotel.isPresent()) {
            return optionalHotel.get();
        } else {
            throw new HotelNotFoundException(String.format("Hotel with ID %d not found", hotelId));
        }
    }

    public List<HotelModel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public void deleteHotelById(int hotelId) throws HotelNotFoundException {
        HotelModel hotel = getHotelById(hotelId);
        hotelRepository.delete(hotel);
    }
}
