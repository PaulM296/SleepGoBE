package com.sleepgo.sleepgo.repositories;

import com.sleepgo.sleepgo.models.RoomModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<RoomModel, Integer> {
    List<RoomModel> findByRoomId(int roomId);
    List<RoomModel> findByRoomType(String roomType);
}
