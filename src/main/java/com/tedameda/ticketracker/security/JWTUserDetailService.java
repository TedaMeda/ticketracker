package com.tedameda.ticketracker.security;

import com.tedameda.ticketracker.user.UserRepository;
import com.tedameda.ticketracker.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author TedaMeda
 * @since 4/17/2024
 */
@Component
@RequiredArgsConstructor
public class JWTUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        return userRepository.findByEmail(userEmail).orElseThrow(()->new UsernameNotFoundException("User not found"));
    }
}
