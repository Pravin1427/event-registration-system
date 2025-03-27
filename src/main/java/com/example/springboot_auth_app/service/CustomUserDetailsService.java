package com.example.springboot_auth_app.service;

import com.example.springboot_auth_app.model.User;
import com.example.springboot_auth_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(username);
        builder.password(user.getPassword());
        builder.roles();

        return builder.build();
    }
    @EventListener
    public void handleOAuth2LoginSuccess(AuthenticationSuccessEvent event) {
        if (event.getAuthentication() instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oAuth2Token = (OAuth2AuthenticationToken) event.getAuthentication();
            OAuth2User oAuth2User = (OAuth2User) oAuth2Token.getPrincipal();
            String username = oAuth2User.getAttribute("login");
            String email = oAuth2User.getAttribute("email");

            if (username != null && userRepository.findByUsername(username).isEmpty()) {
                User newUser = new User();
                newUser.setUsername(username);
                newUser.setEmail(email);
                newUser.setPassword("oauth2user"); //set a default password, since it is not needed.
                userRepository.save(newUser);
            }
        }
    }
}