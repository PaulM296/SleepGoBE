package com.sleepgo.sleepgo.models;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
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
    @Column(name = "wi_fi")
    private boolean wiFi;
    @Column(name = "room_service")
    private boolean roomService;
    private boolean bar;
}
