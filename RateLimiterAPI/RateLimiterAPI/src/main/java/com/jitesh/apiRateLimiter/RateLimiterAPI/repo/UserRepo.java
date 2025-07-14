package com.jitesh.apiRateLimiter.RateLimiterAPI.repo;

import com.jitesh.apiRateLimiter.RateLimiterAPI.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users, Long> {

    Users findByUsername(String username);

}
