package com.sleepgo.sleepgo.models;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "appuser")
public class UserModel {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        UserModel userModel = (UserModel) o;
        return id == userModel.id && Objects.equals(firstName, userModel.firstName) && Objects.equals(lastName, userModel.lastName) && Objects.equals(email, userModel.email) && Objects.equals(phoneNumber, userModel.phoneNumber) && Objects.equals(username, userModel.username) && Objects.equals(password, userModel.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, phoneNumber, username, password);
    }
}
