package com.manpower.backendProject.repositories;

import com.manpower.backendProject.models.leave.LeaveRequest;
import com.manpower.backendProject.models.team.Team;
import com.manpower.backendProject.models.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    Page<LeaveRequest> findByUser(User user, Pageable pageable);

    @Query("SELECT lr FROM LeaveRequest lr JOIN lr.user u WHERE u.team = :team")
    Page<LeaveRequest> findByTeam(@Param("team") Team team, Pageable page);
}
