package com.sleepgo.sleepgo.services;

import com.sleepgo.sleepgo.exceptions.HotelNotFoundException;
import com.sleepgo.sleepgo.exceptions.RoomNotFoundException;
import com.sleepgo.sleepgo.exceptions.RoomTypeNotFound;
import com.sleepgo.sleepgo.models.HotelModel;
import com.sleepgo.sleepgo.models.RoomModel;
import com.sleepgo.sleepgo.repositories.RoomRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    @Resource
    private RoomRepository roomRepository;

    @Resource
    private HotelService  hotelService;

    public RoomModel getRoomById(int roomId) throws RoomNotFoundException {
        Optional<RoomModel> optionalRoom = roomRepository.findByRoomId(roomId);
        if(optionalRoom.isPresent()) {
            return optionalRoom.get();
        } else {
            throw new RoomNotFoundException(String.format("Hotel with ID %d not found", roomId));
        }
    }

    public List<RoomModel> getRoomsByHotelId(int hotelId) {
        return roomRepository.findByHotelId(hotelId);
    }

    public RoomModel updateRoom(int roomId, RoomModel updatedRoom) throws RoomNotFoundException, HotelNotFoundException {
        RoomModel room = getRoomById(roomId);
        room.setRoomType(updatedRoom.getRoomType());
        room.setPrice(updatedRoom.getPrice());
        room.setAvailability(updatedRoom.isAvailability());
        room.setBalcony(updatedRoom.isBalcony());
        room.setAirConditioning(updatedRoom.isAirConditioning());
        room.setKitchenette(updatedRoom.isKitchenette());
        room.setHairdryer(updatedRoom.isHairdryer());
        room.setTv(updatedRoom.isTv());
        return roomRepository.save(room);
    }

    public void deleteRoom(int roomId) throws RoomNotFoundException {
        if(roomRepository.existsById(roomId)) {
            roomRepository.deleteById(roomId);
        } else {
            throw new RoomNotFoundException(String.format("Room with ID %d not found", roomId));
        }
    }

    public List<RoomModel> findAllRoomsByRoomType(String roomType) throws RoomTypeNotFound {
        return roomRepository.findByRoomType(roomType);
    }
}
