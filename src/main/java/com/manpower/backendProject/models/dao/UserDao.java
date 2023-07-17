package com.manpower.backendProject.models.dao;

import com.manpower.backendProject.models.user.Role;
import com.manpower.backendProject.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDao {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private List<Role> roles;
    private boolean enabled;


    public static UserDao buildUserDao(User user){
        return UserDao.builder()
                .id(user.getId())
                .firstname((user.getFirstname()))
                .lastname((user.getLastname()))
                .email(user.getEmail())
                .roles(user.getRoles())
                .enabled(user.isEnabled())
                .build();
    }
}
