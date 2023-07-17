package com.manpower.backendProject.models.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamDao {
    private int id;
    private UserDao manager;
    private List<UserDao> members;
}
