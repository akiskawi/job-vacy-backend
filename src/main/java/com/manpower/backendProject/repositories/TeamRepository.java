package com.manpower.backendProject.repositories;

import com.manpower.backendProject.models.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TeamRepository extends JpaRepository<Team, Integer> {

}
