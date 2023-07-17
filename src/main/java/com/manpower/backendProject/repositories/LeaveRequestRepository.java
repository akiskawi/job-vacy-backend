package com.manpower.backendProject.repositories;

import com.manpower.backendProject.models.leave.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest,Integer> {
    List<LeaveRequest> findAllByRequestsUserId(int requestsUserId);

    LeaveRequest findOneByIdAndRequestsUser_Id(int id, int requestsUserId);
}
