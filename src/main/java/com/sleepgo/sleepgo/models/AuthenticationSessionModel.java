package com.sleepgo.sleepgo.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "authentication_session")
public class AuthenticationSessionModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    int id;
    @Column(name = "user_id")
    int userID;
    String username;
    String token;
    @Column(name = "created_at")
    Timestamp createdAt;

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        AuthenticationSessionModel that = (AuthenticationSessionModel) o;
        return id == that.id && userID == that.userID && Objects.equals(username, that.username) && Objects.equals(token, that.token) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userID, username, token, createdAt);
    }

    @Override
    public String toString() {
        return "AuthenticationSessionModel{" +
                "id=" + id +
                ", userID=" + userID +
                ", username='" + username + '\'' +
                ", token='" + token + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
