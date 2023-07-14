package com.manpower.backendProject.controllers.dao;

import com.manpower.backendProject.request.Number_of_request;
import com.manpower.backendProject.request.Request;
import com.manpower.backendProject.team.Team;
import com.manpower.backendProject.user.Role;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
//    private List<Request> requests;
//    private Team team;
//    private Team teamManager;
//    private List<Number_of_request> remainingDays;
    private boolean enabled;
}
