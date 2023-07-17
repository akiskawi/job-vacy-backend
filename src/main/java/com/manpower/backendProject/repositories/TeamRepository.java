package com.manpower.backendProject.repositories;

import com.manpower.backendProject.models.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Integer> {

}
