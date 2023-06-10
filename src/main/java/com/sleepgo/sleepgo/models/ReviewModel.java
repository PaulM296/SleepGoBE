package com.sleepgo.sleepgo.models;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
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
    @Column(name = "review_text")
    private String reviewText;
}
