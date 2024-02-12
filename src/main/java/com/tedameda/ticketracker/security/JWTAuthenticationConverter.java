package com.tedameda.ticketracker.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;

/**
 * @author TedaMeda
 * @since 2/9/2024
 */
public class JWTAuthenticationConverter implements AuthenticationConverter {
    @Override
    public Authentication convert(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if(authHeader ==null || !authHeader.startsWith("Bearer ")){
            return null;
        }
        String jwt = authHeader.substring(7);
        return new JWTAuthentication(jwt);
    }
}
