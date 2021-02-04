package com.gymapplicationbackend.gymapplicationbackendapi.config;

import com.gymapplicationbackend.gymapplicationbackendapi.model.User;
import com.gymapplicationbackend.gymapplicationbackendapi.service.RedisService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.Date;

public class AuthenticationProviderImpl implements org.springframework.security.authentication.AuthenticationProvider {

    private RedisService redisService;
    private String role;

    public AuthenticationProviderImpl(RedisService service) {
        this.redisService = service;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal() + "";
        String password = authentication.getCredentials() + "";

        if (username == null || username.length() == 0) {
            throw new BadCredentialsException("Username not found.");
        }
        if (password.length() == 0) {
            throw new BadCredentialsException("Wrong password.");
        }

        if (loginValidation(username, password)) {
            SessionUser u = new SessionUser();
            u.setRole(role);
            u.setUsername(username);
            u.setCreated(new Date());
            AuthenticationTokenImpl auth = new AuthenticationTokenImpl(u.getUsername(), Collections.emptyList());
            auth.setAuthenticated(true);
            auth.setDetails(u);
            auth.setRole(role);
            redisService.addToken(u);
            return auth;
        } else {

        }
        return null;
    }

    private boolean loginValidation(String username, String password) {
        User user = redisService.getUser(username);
        String str = new BCryptPasswordEncoder().encode(password + user.getPassword_salt());
        if (user != null && new BCryptPasswordEncoder().matches(password + user.getPassword_salt(), user.getPassword())) {
            role = user.getRole();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean supports(Class<?> type) {
        return type.equals(UsernamePasswordAuthenticationToken.class);
    }

}