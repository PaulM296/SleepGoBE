package com.sleepgo.sleepgo.controllers;

import com.sleepgo.sleepgo.exceptions.HotelNotFoundException;
import com.sleepgo.sleepgo.exceptions.RoomNotFoundException;
import com.sleepgo.sleepgo.exceptions.RoomTypeNotFound;
import com.sleepgo.sleepgo.models.ReservationModel;
import com.sleepgo.sleepgo.models.ReviewModel;
import com.sleepgo.sleepgo.services.ReviewService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Resource
    private ReviewService reviewService;

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
}
