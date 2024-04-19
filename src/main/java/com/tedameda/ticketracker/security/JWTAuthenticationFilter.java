package com.tedameda.ticketracker.security;

import com.tedameda.ticketracker.user.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author TedaMeda
 * @since 4/2/2024
 */
@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final UserDetailsService userService;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String jwt = getTokenFromRequest(request);
        if(jwt!=null){
            if(JwtUtils.validateToket(jwt)){
                var userEmail = JwtUtils.getUserEmail(jwt);
                var user = userService.loadUserByUsername(userEmail);
                var authenticationToken = new UsernamePasswordAuthenticationToken(user, null,user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
    private String getTokenFromRequest(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if(authHeader ==null || !authHeader.startsWith("Bearer ")){
            return null;
        }
        return authHeader.substring(7);
    }
}
