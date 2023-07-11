package com.manpower.backendProject.user;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public enum Role {
    USER,
    MANAGER,
    ADMIN;


    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = new ArrayList<SimpleGrantedAuthority>();

//        authorities.add(new SimpleGrantedAuthority( this.name()));
//        authorities.add(new SimpleGrantedAuthority("ROLE_" + USER.name()));
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.name()));
//        return authorities;
    }
}
