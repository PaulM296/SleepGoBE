package com.sleepgo.sleepgo.controllers;

import com.sleepgo.sleepgo.exceptions.*;
import com.sleepgo.sleepgo.models.ReservationModel;
import com.sleepgo.sleepgo.models.ReviewModel;
import com.sleepgo.sleepgo.services.AuthenticationService;
import com.sleepgo.sleepgo.services.ReviewService;
import org.hibernate.SessionException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {
    @Resource
    private ReviewService reviewService;

    @Resource
    private AuthenticationService authenticationService;
    @PostMapping
    public ReviewModel createReview(@RequestBody ReviewModel review) {
        return reviewService.saveReview(review);
    }

    @GetMapping("/hotel/{hotelId}")
    public List<ReviewModel> getReviewsByHotelId(@PathVariable int hotelId) throws HotelNotFoundException {
        return reviewService.getReviewsByHotelId(hotelId);
    }

    @GetMapping("/room/{roomId}")
    public List<ReviewModel> getReviewsByRoomId(@PathVariable int roomId) throws RoomNotFoundException {
        return reviewService.getReviewsByRoomId(roomId);
    }

    @DeleteMapping("/{reviewId}")
    public void deleteReview(@PathVariable int reviewId) {
        reviewService.deleteReview(reviewId);
    }

    @GetMapping("/{username}")
    public List<ReviewModel> getReviewsByUsername(@PathVariable String username, @RequestHeader("custom-token") String token) throws UserNotFoundException, InvalidTokenException{
        if (!authenticationService.checkAuthenticationSessionExists(username)) {
            throw new SessionException("Session does not exist");
        }
        if (authenticationService.checkAuthenticationToken(username, token)) {
            return reviewService.getReviewsByUsername(username);
        } else {
            throw new InvalidTokenException("The token is invalid");
        }
    }

//    @GetMapping("/{reviewId}")
//    public List<ReviewModel> getReviewsByReviewId(@PathVariable int reviewId) throws ReviewNotFoundException {
//        return reviewService.getReviewsByReviewId(reviewId);
//    }
}
