package com.java.authentication.springbootauthentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for simpler API testing (consider enabling for production web apps)
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/signup", "/login").permitAll() // Allow unauthenticated access to /signup and /login
                .anyRequest().authenticated() // All other requests require authentication
            );
            // .httpBasic(Customizer.withDefaults()); // Optional: If you plan to use HTTP Basic Authentication

        return http.build();
    }
}
