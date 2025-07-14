package com.jitesh.apiRateLimiter.RateLimiterAPI.service;


import com.jitesh.apiRateLimiter.RateLimiterAPI.model.Users;
import com.jitesh.apiRateLimiter.RateLimiterAPI.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  private BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder(12);

  @Autowired
  AuthenticationManager authManager;

  @Autowired
  private JWTService jwtService;

  @Autowired
  private UserRepo repo;


    public String register(Users user){
        user.setPassword(encoder.encode(user.getPassword()));
         repo.save(user);
         return "SUCCESS";
    }

    public String verify(Users user) {

        Authentication authentication =
                authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername() , user.getPassword()));

        if(authentication.isAuthenticated())
            return jwtService.generateToken(user.getUsername());

        return "FAIL";
    }
}
