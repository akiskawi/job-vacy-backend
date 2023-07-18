package com.manpower.backendProject.repositories;

import com.manpower.backendProject.models.leave.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest,Integer> {
    List<LeaveRequest> findAllByUserId(int userId);

    LeaveRequest findOneByIdAndUser_Id(int leaveRequestId, int userId);
}
