package com.sleepgo.sleepgo.repositories;

import com.sleepgo.sleepgo.models.HotelModel;
import com.sleepgo.sleepgo.models.ReviewModel;
import com.sleepgo.sleepgo.models.RoomModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewModel, Integer> {
    ReviewModel save(ReviewModel review);   // mai trebuie vazut aici
    List<ReviewModel> findByHotelId(int hotelId);
    List<ReviewModel> findByRoomId(int roomId);
}
