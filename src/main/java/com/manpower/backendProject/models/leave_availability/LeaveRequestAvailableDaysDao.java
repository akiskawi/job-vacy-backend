package com.manpower.backendProject.models.leave_availability;

import com.manpower.backendProject.models.leave.LeaveRequestDao;
import com.manpower.backendProject.models.leave.LeaveRequestTYPE;
import jakarta.annotation.security.DenyAll;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveRequestAvailableDaysDao {
    private short taken;
    private short remaining;
    private LeaveRequestTYPE type;

    public static LeaveRequestAvailableDaysDao  leaveRequestAvailableDaysConverter(LeaveRequestAvailableDays leaveRequestAvailableDays) {
        return LeaveRequestAvailableDaysDao
                .builder()
                .type(leaveRequestAvailableDays.getType())
                .remaining(leaveRequestAvailableDays.getRemaining())
                .taken(leaveRequestAvailableDays.getTaken())
                .build();
    }
}
