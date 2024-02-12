package com.tedameda.ticketracker.security;

import com.tedameda.ticketracker.user.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @author TedaMeda
 * @since 2/9/2024
 */
public class JWTAuthenticationManager implements AuthenticationManager {
    JWTService jwtService;
    UserService userService;

    public JWTAuthenticationManager(JWTService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(authentication instanceof JWTAuthentication jwtAuthentication){
            var jwt = jwtAuthentication.getCredentials();
            var userId = jwtService.retrieveUserId(jwt);
            jwtAuthentication.setUserEntity(userService.getUser(userId));
            return jwtAuthentication;
        }
        throw new IllegalAccessError("Cannot authenticate this non-JWT authentication");
    }
}
