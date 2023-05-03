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
}
