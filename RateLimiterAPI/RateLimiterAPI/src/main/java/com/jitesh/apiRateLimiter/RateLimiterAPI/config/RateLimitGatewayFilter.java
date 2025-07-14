package com.jitesh.apiRateLimiter.RateLimiterAPI.config;

import com.jitesh.apiRateLimiter.RateLimiterAPI.service.RateLimitService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class RateLimitGatewayFilter extends OncePerRequestFilter {

    private RateLimitService rateLimitService;

    public RateLimitGatewayFilter(RateLimitService service){
        rateLimitService = service;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String userName = "";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth != null && auth.isAuthenticated()){
            userName = auth.getName();
        }

        if(!rateLimitService.isAllowed(userName)){
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("Rate Limit Exceeded");
            return;
        }
        filterChain.doFilter(request,response);

    }
}
