package com.example.springboot_auth_app.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class OtpVerificationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().equals("/dashboard")) {
            Boolean otpVerified = (Boolean) request.getSession().getAttribute("otpVerified");
            if (otpVerified == null || !otpVerified) {
                response.sendRedirect("/verify-otp"); // Redirect to OTP page if not verified.
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}