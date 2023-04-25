package com.sleepgo.sleepgo.repositories;

import com.sleepgo.sleepgo.models.AuthenticationSessionModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthenticationSessionRepository extends JpaRepository<AuthenticationSessionModel, Integer> {
    List<AuthenticationSessionModel> findByUsername(String username);
}
