package com.tedameda.ticketracker.security;

import com.tedameda.ticketracker.user.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author TedaMeda
 * @since 2/9/2024
 */
@Setter
@Getter
public class JWTAuthentication implements Authentication {
    private String jwt;
    private UserEntity userEntity;

    public JWTAuthentication(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getCredentials() {
        return jwt;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.userEntity;
    }

    @Override
    public boolean isAuthenticated() {
        return (userEntity!=null);
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }
}
