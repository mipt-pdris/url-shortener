package ru.netology.testcase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class UrlShortenerStarter {
    public static void main(String[] args) {
        SpringApplication.run(UrlShortenerStarter.class, args);
        log.info("=== URL SHORTONER SERVICE STARTED ===");
    }
}