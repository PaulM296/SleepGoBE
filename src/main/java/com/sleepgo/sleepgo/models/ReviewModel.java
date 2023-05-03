package com.sleepgo.sleepgo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "reviews")
public class ReviewModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    @Id
    private int reviewId;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "hotel_id")
    private int hotelId;
    @Column(name = "room_id")
    private int roomId;
    @Column(name = "review_text")
    private String reviewText;
}
