package com.manpower.backendProject.models.user;

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
    private long id;
    private String firstname;
    private String lastname;
    private String email;
    private List<Role> roles;
    private boolean enabled;


    public static UserDao userDaoConverter(User user){
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
