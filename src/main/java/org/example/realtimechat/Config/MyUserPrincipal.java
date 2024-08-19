package org.example.realtimechat.Config;

import org.example.realtimechat.Models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.Collections;

public class MyUserPrincipal implements UserDetails {
    private final User user;

    private final Collection<GrantedAuthority> authorities;

    public MyUserPrincipal(User user) {
        this.user = user;
        this.authorities = Collections.singletonList(
                new SimpleGrantedAuthority(user.getRole())
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    public String getFirstName() {
        return user.getFirstName();
    }

    public String getLastName() {
        return user.getLastName();
    }

    public String getRole() {
        return user.getRole();
    }

    public Integer getUserId() {
        return user.getUserId();
    }

}
