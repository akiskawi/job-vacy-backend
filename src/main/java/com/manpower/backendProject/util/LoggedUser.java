package com.manpower.backendProject.util;

import com.manpower.backendProject.models.user.User;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoggedUser {
   private final static User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//   private  static long userId;


    public static long getId() {
        return loggedUser.getId();
    }
//    public static String getPassword(){
//        return loggedUser.getPassword();
//    }
}
