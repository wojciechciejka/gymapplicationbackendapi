package com.gymapplicationbackend.gymapplicationbackendapi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AuthenticationTokenImpl extends AbstractAuthenticationToken {

    @Setter
    private String username;

    @Getter
    @Setter
    private String role;

    public AuthenticationTokenImpl(String principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.username = principal;
    }

    public void authenticate() {
        if (getDetails() != null && getDetails() instanceof SessionUser && !((SessionUser) getDetails()).hasExpired()) {
            role = ((SessionUser) getDetails()).getRole();
            setAuthenticated(true);
        } else {
            setAuthenticated(false);
        }
    }

    @Override
    public Object getCredentials() {
        return "";
    }

    @Override
    public Object getPrincipal() {
        return username != null ? username.toString() : "";
    }
}
