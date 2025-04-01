package com.example.springboot_auth_app.controller;

import com.example.springboot_auth_app.model.User;
import com.example.springboot_auth_app.repository.UserRepository;
import com.example.springboot_auth_app.service.EmailService;
import com.example.springboot_auth_app.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@Controller
public class OtpController {

    @Autowired
    private OtpService otpService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/verify-otp")
    public String showOtpPage() {
        return "otp-verification";
    }

    @PostMapping("/send-otp")
    public String sendOtp(Authentication authentication, Model model) throws IOException {
        String username = authentication.getName();
        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            String otp = otpService.generateOtp(user);
            emailService.sendOtpEmail(user.getEmail(), otp);
            model.addAttribute("message", "OTP sent to your email.");
        } else {
            model.addAttribute("error", "User not found.");
        }

        return "otp-verification";
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam("otp") String otp, Authentication authentication, Model model) {
        String username = authentication.getName();
        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (otpService.verifyOtp(user, otp)) {
                otpService.clearOtp(user);
                return "redirect:/dashboard";
            } else {
                model.addAttribute("error", "Invalid or expired OTP.");
            }
        } else {
            model.addAttribute("error", "User not found.");
        }

        return "otp-verification";
    }
}
