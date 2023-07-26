package com.manpower.backendProject.repositories;

import com.manpower.backendProject.models.leave.LeaveRequestTYPE;
import com.manpower.backendProject.models.leave_availability.LeaveRequestAvailableDays;
import com.manpower.backendProject.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LeaveRequestAvailableDaysRepository extends JpaRepository<LeaveRequestAvailableDays,Long> {
    List<LeaveRequestAvailableDays> findByUsersLeaveRequestsRemainingDays(User user);

    List<LeaveRequestAvailableDays> findByUsersLeaveRequestsRemainingDaysAndType(User user, LeaveRequestTYPE type);


}
