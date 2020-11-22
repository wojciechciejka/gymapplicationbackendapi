package com.gymapplicationbackend.gymapplicationbackendapi.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gymapplicationbackend.gymapplicationbackendapi.service.RedisService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenAuthenticationService {

    private static final String TOKEN_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer";
    private RedisService service;


    public TokenAuthenticationService(RedisService service) {
        this.service = service;
    }


    public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_HEADER); // 3
        if (token != null && token.startsWith(TOKEN_PREFIX)) {
            String userName = JWT.require(Algorithm.HMAC256("secret")) // 4
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, "")) // 5
                    .getSubject(); // 6
            if (userName != null) {
                SessionUser sessionUser = service.getSession(userName);
                AuthenticationTokenImpl auth = new AuthenticationTokenImpl(userName, Collections.emptyList());
                auth.setDetails(sessionUser);
                auth.authenticate();
                return auth;
            }
        }
        return null;
    }
}

