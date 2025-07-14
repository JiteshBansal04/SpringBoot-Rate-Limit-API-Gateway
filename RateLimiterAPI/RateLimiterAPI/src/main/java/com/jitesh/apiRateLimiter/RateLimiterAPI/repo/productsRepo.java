package com.jitesh.apiRateLimiter.RateLimiterAPI.repo;


import com.jitesh.apiRateLimiter.RateLimiterAPI.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface productsRepo extends JpaRepository<Products,Long> {
}
