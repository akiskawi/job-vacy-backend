package com.manpower.backendProject.repositories;

import com.manpower.backendProject.models.leave_types.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveTypeRepository extends JpaRepository<LeaveType, Long> {
}
