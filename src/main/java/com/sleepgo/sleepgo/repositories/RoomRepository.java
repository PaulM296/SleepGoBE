package com.sleepgo.sleepgo.repositories;

import com.sleepgo.sleepgo.models.RoomModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<RoomModel, Integer> {
    Optional<RoomModel> findByRoomId(int roomId);
    List<RoomModel> findByRoomType(String roomType);
    List<RoomModel> findByHotelId(int hotelId);
}
