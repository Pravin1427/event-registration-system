package com.example.springboot_auth_app.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        logger.info("Logging out user...");
        request.getSession().invalidate(); // Invalidate the session
        SecurityContextHolder.clearContext(); // Clear Spring Security context

        logger.info("Session invalidated and SecurityContext cleared.");
        return "redirect:/login?logout"; // Redirect to login page after logout
    }
}
