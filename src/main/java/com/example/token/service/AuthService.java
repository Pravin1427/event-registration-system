package com.example.token.service;

import com.example.token.model.User;
import com.example.token.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    public String registerUser(String username, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already taken");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setToken(tokenService.generateToken());

        userRepository.save(user);
        return user.getToken();
    }

    public String loginUser(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("Invalid username or password");
        }

        User user = userOpt.get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        // Generate new token on login
        user.setToken(tokenService.generateToken());
        userRepository.save(user);

        return user.getToken();
    }

    public String refreshToken(String oldToken) {
        Optional<User> userOpt = userRepository.findByToken(oldToken);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("Invalid token");
        }

        User user = userOpt.get();
        String newToken = tokenService.generateToken();
        user.setToken(newToken);
        userRepository.save(user);

        return newToken;
    }
}
