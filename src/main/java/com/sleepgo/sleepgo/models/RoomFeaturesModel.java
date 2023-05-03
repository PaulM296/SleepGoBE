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
@Table(name = "room_features")
public class RoomFeaturesModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feature_id")
    @Id
    private int featureId;
    @Column(name = "room_id")
    private int roomId;
    private boolean balcony;
    private boolean airConditioning;
    private boolean kitchenette;
    private boolean hairdryer;
    private boolean tv;
}
