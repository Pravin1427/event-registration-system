package com.example.springboot_auth_app.controller;

import com.example.springboot_auth_app.model.User;
import com.example.springboot_auth_app.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Show registration form")
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Redirect to login on success"),
            @ApiResponse(responseCode = "200", description = "Registration page with error on failure")
    })
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        try {
            userService.registerUser(user);
        } catch (RuntimeException e) {
            model.addAttribute("usernameError", e.getMessage());
            return "register";
        }
        return "redirect:/login";
    }

    @Operation(summary = "Show login form")
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @Operation(summary = "Show dashboard")
    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication authentication) {
        String username;
        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
            username = oAuth2User.getAttribute("login");
        } else {
            username = authentication.getName();
        }
        model.addAttribute("username", username);
        return "dashboard";
    }
}