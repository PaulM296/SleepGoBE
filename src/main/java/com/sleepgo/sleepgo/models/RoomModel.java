package com.sleepgo.sleepgo.models;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "room")
public class RoomModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    @Id
    private int roomId;
    @Column(name = "hotel_id")
    private int hotelId;
    @Column(name = "room_type")
    private String roomType;
    private int price;
    private boolean availability;
    private boolean balcony;
    @Column(name = "air_conditioning")
    private boolean airConditioning;
    private boolean kitchenette;
    private boolean hairdryer;
    private boolean tv;
}
