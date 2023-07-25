package com.manpower.backendProject.models.team;

import com.manpower.backendProject.models.user.UserDao;
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
    private long id;
    private UserDao manager;
    private List<UserDao> members;
}
