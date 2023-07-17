package com.manpower.backendProject.repositories;

import com.manpower.backendProject.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
    List<User> findUsersByTeam_Id(Integer team_id);
    Optional<User> findByTeamManagerId(Integer teamManagerId);
}
