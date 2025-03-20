package com.example.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggingService {
    private static final Logger log = LoggerFactory.getLogger(LoggingService.class);

    public void logEvent(String eventType, String message) {
        log.info("<log><type>{}</type><message>{}</message></log>", eventType, message);
    }

    public void logError(String errorType, String errorMessage) {
        log.error("<log><type>{}</type><message>{}</message></log>", errorType, errorMessage);
    }
}
