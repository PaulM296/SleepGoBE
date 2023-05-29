package com.sleepgo.sleepgo.repositories;

import com.sleepgo.sleepgo.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    List<UserModel> findByUsername(String username);
    List<UserModel> findByEmail(String email);
    Optional<UserModel> findById(int id);

}
