package com.demo2springsecurity.security;

import com.demo2springsecurity.entiy.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class UserPrincipal implements UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;
    String userName;
    String password = null;
    String role = null;
    Set<SimpleGrantedAuthority> authorities;

    public UserPrincipal(UserInfo user) {
        userName = user.getUsername();
        password = user.getPassword();
        role = user.getRoles();
        authorities = Collections.singleton(new SimpleGrantedAuthority(role));
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
