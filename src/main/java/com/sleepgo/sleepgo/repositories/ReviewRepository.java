package com.sleepgo.sleepgo.repositories;

import com.sleepgo.sleepgo.models.HotelModel;
import com.sleepgo.sleepgo.models.ReservationModel;
import com.sleepgo.sleepgo.models.ReviewModel;
import com.sleepgo.sleepgo.models.RoomModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReviewRepository extends JpaRepository<ReviewModel, Integer> {
    List<ReviewModel> findByHotelId(int hotelId);

    List<ReviewModel> findByUserId(int userId);

    List<ReviewModel> findByReviewId(int reviewId);

    List<ReviewModel> deleteById(int reviewId);

    List<ReviewModel> deleteByUserId(int userId);

    List<ReviewModel> deleteByHotelId(int hotelId);
}
