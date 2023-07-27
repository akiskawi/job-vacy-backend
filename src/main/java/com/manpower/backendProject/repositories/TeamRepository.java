package com.manpower.backendProject.repositories;

import com.manpower.backendProject.models.team.Team;
import com.manpower.backendProject.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    Team findByManager(User manager);

}
