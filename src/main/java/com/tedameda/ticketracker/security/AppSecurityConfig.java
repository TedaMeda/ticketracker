package com.tedameda.ticketracker.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author TedaMeda
 * @since 2/9/2024
 */
@Configuration
@EnableWebSecurity
public class AppSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/users/**").permitAll()
                .requestMatchers("/**","/departments/**").permitAll()
                .anyRequest().authenticated()
        ).csrf(AbstractHttpConfigurer::disable).httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
