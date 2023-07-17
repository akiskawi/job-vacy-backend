package com.manpower.backendProject.models.dao;

import com.manpower.backendProject.models.dao.UserDao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String token;
    private UserDao userDao;

}
