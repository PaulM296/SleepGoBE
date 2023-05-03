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
@Table(name = "hotel_location")
public class HotelLocationModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column(name = "hotel_id")
    private int hotelId;
    private String country;
    private String city;
    private String address;
    private String zipCode;

}
