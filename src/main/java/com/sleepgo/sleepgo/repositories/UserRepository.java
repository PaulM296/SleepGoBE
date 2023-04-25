package com.sleepgo.sleepgo.repositories;

import com.sleepgo.sleepgo.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
    List<UserModel> findByUsername(String username);
    List<UserModel> findByEmail(String email);
}
