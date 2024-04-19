package com.tedameda.ticketracker.security;

import com.tedameda.ticketracker.user.Role;
import jakarta.servlet.FilterChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author TedaMeda
 * @since 4/2/2024
 */
@Configuration
@EnableWebSecurity
public class AppSecurityConfig {
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final JWTAuthenticationFilter jwtAuthenticationFilter;
    public AppSecurityConfig(AuthenticationEntryPoint authenticationEntryPoint, JWTAuthenticationFilter jwtAuthenticationFilter) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//       disable cors and csrf
        http.cors(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(
            (authorize)->authorize
                    .requestMatchers("/users/register").permitAll()
                    .requestMatchers("/users/login").permitAll()
                    .requestMatchers("/update-role").hasAnyAuthority(Role.SUPER_ADMIN.toString())
                    .requestMatchers(HttpMethod.POST,"/departments").hasAnyAuthority(Role.SUPER_ADMIN.toString())
                    .requestMatchers(HttpMethod.DELETE,"/departments/{department-name}").hasAnyAuthority(Role.SUPER_ADMIN.toString())
                    .requestMatchers(HttpMethod.GET,"/tickets").hasAnyAuthority(Role.ADMIN.toString(),Role.SUPER_ADMIN.toString())
                    .requestMatchers("/tickets/{ticket-id}/assign-ticket", "/tickets/{ticket-id}/update-status").hasAnyAuthority(Role.ADMIN.toString())
                    .anyRequest().authenticated()
        );
        //Authentication Entry Point -> exception handling
        http.exceptionHandling(
                exceptionConfig->exceptionConfig.authenticationEntryPoint(authenticationEntryPoint)
        );
        //session policy -> STATELESS
        http.sessionManagement(
                sessionConfig->sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
//      Add JWT Filter
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
