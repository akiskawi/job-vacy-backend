package com.manpower.backendProject.repositories;

import com.manpower.backendProject.models.team.Team;
import com.manpower.backendProject.models.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.team = :team")
    Page<User> findUsersByTeam(@Param("team") Team team, Pageable page);
}
