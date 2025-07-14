package com.jitesh.apiRateLimiter.RateLimiterAPI.controller;


import com.jitesh.apiRateLimiter.RateLimiterAPI.model.Users;
import com.jitesh.apiRateLimiter.RateLimiterAPI.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Users user){
        return new ResponseEntity<>(authService.register(user) , HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Users user){
        return new ResponseEntity<>(authService.verify(user) , HttpStatus.OK);


    }




}
