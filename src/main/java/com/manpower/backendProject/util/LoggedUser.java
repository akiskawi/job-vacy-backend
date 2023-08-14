package com.manpower.backendProject.util;

import com.manpower.backendProject.models.user.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoggedUser {
    private final static User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//   private  static long userId;

    public static long getId() {
        return loggedUser.getId();
    }

    public static User get() {
        return loggedUser;
    }

//    public static Role getRole(){ return loggedUser.getR;}
//    public static String getPassword(){
//        return loggedUser.getPassword();
//    }
}
