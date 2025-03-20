package com.example.logging.controller;

import com.example.logging.LoggingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/logs")
public class LoggingController {

    private final LoggingService loggingService;

    public LoggingController(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    @PostMapping("/info")
    public ResponseEntity<String> logInfo(@RequestBody Map<String, String> request) {
        loggingService.logEvent(request.get("type"), request.get("message"));
        return ResponseEntity.ok("Log recorded");
    }

    @PostMapping("/error")
    public ResponseEntity<String> logError(@RequestBody Map<String, String> request) {
        loggingService.logError(request.get("type"), request.get("message"));
        return ResponseEntity.ok("Error log recorded");
    }
}
