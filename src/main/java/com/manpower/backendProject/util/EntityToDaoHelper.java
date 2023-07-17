package com.manpower.backendProject.util;

import com.manpower.backendProject.models.dao.UserDao;
import com.manpower.backendProject.models.user.User;

public class EntityToDaoHelper {
    public static UserDao userToUserDao(User user) {
        UserDao userDao = UserDao.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .roles(user.getRoles())
//      .requests(user.getRequests())
//      .team(user.getTeam())
//      .teamManager(user.getTeamManager())
//      .remainingDays((user.getRemainingDays()))
                .enabled(user.isEnabled())
                .build();
        return userDao;
    }
}
