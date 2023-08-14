package com.manpower.backendProject.repositories;

import com.manpower.backendProject.models.leave.LeaveRequest;
import com.manpower.backendProject.models.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest,Long> {

    Page<LeaveRequest> findByRequestsUser(User requestsUser, Pageable pageable);
//    Page<LeaveRequest> findByUser_Id(long user_id, Pageable pageable);
    List<LeaveRequest> findAllByRequestsUserId(long requestsUserId);

    LeaveRequest findOneByIdAndRequestsUser_Id(long id, long requestsUserId);
}
