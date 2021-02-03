package com.gymapplicationbackend.gymapplicationbackendapi.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    @Value("${jwt.expirationTime}")
    private Long expirationTime;
    @Value("${jwt.secret}")
    private String secret;


    public JWTLoginFilter(String url, AuthenticationManager authenticationManager) {//, TokenAuthenticationService service) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse hsr1) throws AuthenticationException, IOException, ServletException {
        LoginCredentials credentials = new ObjectMapper().readValue(httpServletRequest.getInputStream(), LoginCredentials.class);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword());
        return getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication)
            throws IOException, ServletException {

        AuthenticationTokenImpl auth = (AuthenticationTokenImpl) authentication;
        String token = JWT.create()
                .withSubject((String) auth.getPrincipal())
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600000))
                .sign(Algorithm.HMAC256("secret"));
        response.addHeader("Authorization", "Bearer" + token);
        response.addHeader("Role", auth.getRole());
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
