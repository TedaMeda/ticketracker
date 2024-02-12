package com.tedameda.ticketracker.security;

import com.tedameda.ticketracker.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author TedaMeda
 * @since 2/9/2024
 */
@Configuration
public class JWTConfig {
    JWTService jwtService;
    UserService userService;

    public JWTConfig(JWTService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() throws Exception{
        return new JWTAuthenticationFilter(
                new JWTAuthenticationManager(jwtService,userService)
        );
    }
}
