package com.sleepgo.sleepgo.models;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "hotel")
public class HotelModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    @Id
    private int hotelId;
    @Column(name = "hotel_name")
    private String hotelName;
    @Column(name = "hotel_description")
    private String hotelDescription;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String email;
    private String country;
    private String city;
    private String address;
    private String zip_code;
}
