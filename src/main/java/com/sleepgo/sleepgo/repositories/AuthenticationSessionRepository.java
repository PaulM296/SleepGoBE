package com.sleepgo.sleepgo.repositories;

import com.sleepgo.sleepgo.models.AuthenticationSessionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AuthenticationSessionRepository extends JpaRepository<AuthenticationSessionModel, Integer> {
    List<AuthenticationSessionModel> findByUsername(String username);
}
