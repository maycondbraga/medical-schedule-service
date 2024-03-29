package com.bmaycon.schedule.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    @Value("${jwt.secret}")
    private String secretKey;
}