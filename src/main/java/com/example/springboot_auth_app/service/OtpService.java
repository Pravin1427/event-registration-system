package com.example.springboot_auth_app.service;

import com.example.springboot_auth_app.model.User;
import com.example.springboot_auth_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OtpService {

    @Autowired
    private UserRepository userRepository;

    private static final int OTP_EXPIRATION_MINUTES = 5;
    private static final int OTP_LENGTH = 6;

    private final SecureRandom random = new SecureRandom();

    public String generateOtp(User user) {
        String otp = String.format("%06d", random.nextInt(999999));
        user.setOtp(otp);
        user.setOtpExpiration(LocalDateTime.now().plusMinutes(OTP_EXPIRATION_MINUTES));
        userRepository.save(user);
        return otp;
    }

    public boolean verifyOtp(User user, String otp) {
        return user.getOtp() != null &&
                user.getOtp().equals(otp) &&
                user.getOtpExpiration().isAfter(LocalDateTime.now());
    }

    public void clearOtp(User user) {
        user.setOtp(null);
        user.setOtpExpiration(null);
        userRepository.save(user);
    }
}
