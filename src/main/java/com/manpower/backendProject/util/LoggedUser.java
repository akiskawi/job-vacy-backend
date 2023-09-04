package com.manpower.backendProject.util;

import com.manpower.backendProject.models.user.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoggedUser {
    public User get() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
