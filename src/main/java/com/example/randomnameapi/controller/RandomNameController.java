package com.example.randomnameapi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class RandomNameController {

    @GetMapping("/random-names")
    public List<String> getRandomNames() {
        List<String> names = new ArrayList<>();
        Random random = new Random();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        for (int i = 0; i < 10; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 10; j++) {
                sb.append(characters.charAt(random.nextInt(characters.length())));
            }
            names.add(sb.toString());
        }
        return names;
    }
}