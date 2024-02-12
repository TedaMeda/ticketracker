package com.tedameda.ticketracker.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFilter;

/**
 * @author TedaMeda
 * @since 2/9/2024
 */
public class JWTAuthenticationFilter extends AuthenticationFilter {
    private JWTAuthenticationManager jwtAuthenticationManager;
    public JWTAuthenticationFilter(JWTAuthenticationManager jwtAuthenticationManager) {
        super(jwtAuthenticationManager, new JWTAuthenticationConverter());
        this.setSuccessHandler(((request, response, authentication) -> {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }));
    }
}
