package com.sleepgo.sleepgo.models;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "appuser")
public class UserModel {
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    private int id;
    @Column(name= "first_name")
    private String firstName;
    @Column(name= "last_name")
    private String lastName;
    private String email;
    @Column(name= "phone_number")
    private String phoneNumber;
    private String username;
    private String password;

}
