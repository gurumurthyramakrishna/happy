package com.example.happymop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // API is called from same-origin JS; if you enable csrf, adapt client to send token
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/bookings/**", "/api/workers/**", "/api/admin/applications/**", "/api/admin/applications", "/**/*.html", "/", "/index.html", "/**").permitAll()
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
