package com.gc.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by maurice on 8/20/17.
 * Ref: http://www.baeldung.com/spring-security-authentication-with-a-database
 */
public class CustomUserDetails implements UserDetails{
    private User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    public String getPassword() {
        return user.getPassword();
    }

    public String getUsername() {
        return user.getUsername();
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }
}
