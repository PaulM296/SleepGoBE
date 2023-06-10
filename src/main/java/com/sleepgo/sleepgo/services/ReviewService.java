package com.sleepgo.sleepgo.services;

import com.sleepgo.sleepgo.exceptions.*;
import com.sleepgo.sleepgo.models.ReviewModel;
import com.sleepgo.sleepgo.models.UserModel;
import com.sleepgo.sleepgo.repositories.ReviewRepository;
import com.sleepgo.sleepgo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReviewService {
    @Resource
    private ReviewRepository reviewRepository;
    @Resource
    private UserRepository userRepository;

    public ReviewModel saveReview(ReviewModel review) {
        return reviewRepository.save(review);
    }

    public List<ReviewModel> getReviewsByHotelId(int hotelId) throws HotelNotFoundException {
        return reviewRepository.findByHotelId(hotelId);
    }

    public List<ReviewModel> getReviewsByReviewId(int reviewId) throws ReviewNotFoundException {
        return reviewRepository.findByReviewId(reviewId);
    }

    public void deleteReview(int reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    public void deleteReviewByUserId(int userId) {
        reviewRepository.deleteByUserId(userId);
    }

    public List<ReviewModel> getReviewsByUsername(String username) throws UserNotFoundException {
        List<UserModel> users = userRepository.findByUsername(username);
        if (users.isEmpty()) {
            throw new UserNotFoundException(String.format("The user with the %s username does not exist", username));
        }
        int userId = users.get(0).getId();
        return reviewRepository.findByUserId(userId);
    }

}
