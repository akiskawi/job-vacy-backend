package com.manpower.backendProject.controllers.dao;

import com.manpower.backendProject.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUser {
    private String firstname;
    private String lastname;
    private String password;
    private List<Role> roles;
}
