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
@Table(name = "amenities")
public class AmenitiesModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "amenity_id")
    @Id
    private int amenityId;
    @Column(name = "hotel_id")
    private int hotelId;
    private boolean pool;
    private boolean fitness;
    private boolean restaurant;

}
