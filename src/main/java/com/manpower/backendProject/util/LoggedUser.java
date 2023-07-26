package com.manpower.backendProject.util;

import com.manpower.backendProject.models.user.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoggedUser {

    public static long getId(){
        var loggedUser  =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return loggedUser.getId();
    }
}
