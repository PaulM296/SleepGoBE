package com.sleepgo.sleepgo.services;

import com.sleepgo.sleepgo.exceptions.HotelNotFoundException;
import com.sleepgo.sleepgo.exceptions.RoomNotFoundException;
import com.sleepgo.sleepgo.exceptions.RoomTypeNotFound;
import com.sleepgo.sleepgo.models.ReviewModel;
import com.sleepgo.sleepgo.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReviewService {
    @Resource
    private ReviewRepository reviewRepository;

    public ReviewModel saveReview(ReviewModel review) {
        return reviewRepository.save(review);
    }

    public List<ReviewModel> getReviewsByHotelId(int hotelId) throws HotelNotFoundException {
        return reviewRepository.findByHotelId(hotelId);
    }

    public List<ReviewModel> getReviewsByRoomId(int roomId) throws RoomNotFoundException {
        return reviewRepository.findByRoomId(roomId);
    }

    public void deleteReview(int reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    public void deleteReviewByUserId(int userId) {
        reviewRepository.deleteByUserId(userId);
    }
}
