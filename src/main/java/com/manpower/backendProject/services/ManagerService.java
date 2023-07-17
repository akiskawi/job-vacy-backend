package com.manpower.backendProject.services;

import com.manpower.backendProject.models.dao.TeamDao;
import com.manpower.backendProject.models.dao.UserDao;
import com.manpower.backendProject.repositories.UserRepository;
import com.manpower.backendProject.util.EntityToDaoHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final UserRepository repository;
    public ResponseEntity<Object> getTeamMembers(Integer team_id) {
        var manager = repository.findByTeamManagerId(team_id);
        var users = repository.findUsersByTeam_Id(team_id);
        UserDao managerDao = null;
        if (manager.isPresent()) {
            managerDao = EntityToDaoHelper.userToUserDao(manager.get());
        }
        List<UserDao> usersDao = users.stream().map(EntityToDaoHelper::userToUserDao).toList();
        TeamDao teamDao = TeamDao.builder()
                .id(team_id)
                .manager(managerDao)
                .members(usersDao)
                .build();
        return ResponseEntity.ok(teamDao);
    }
}
