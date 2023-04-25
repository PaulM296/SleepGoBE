package com.sleepgo.sleepgo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    int user_id;
    String username;
    String token;
    Timestamp createdAt;

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        AuthenticationSessionModel that = (AuthenticationSessionModel) o;
        return id == that.id && user_id == that.user_id && Objects.equals(username, that.username) && Objects.equals(token, that.token) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user_id, username, token, createdAt);
    }

    @Override
    public String toString() {
        return "AuthenticationSessionModel{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", username='" + username + '\'' +
                ", token='" + token + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
