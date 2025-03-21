package com.example.token.controller;

import com.example.token.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        return authService.registerUser(username, password);
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        return authService.loginUser(username, password);
    }

    @PostMapping("/refresh")
    public String refresh(@RequestParam String token) {
        return authService.refreshToken(token);
    }
}
