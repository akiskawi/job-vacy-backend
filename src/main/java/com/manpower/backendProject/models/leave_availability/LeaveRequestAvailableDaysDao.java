package com.manpower.backendProject.models.leave_availability;

import com.manpower.backendProject.models.leave_types.LeaveType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveRequestAvailableDaysDao {
    private long id;
    private short taken;
    private short remaining;
    private LeaveType type;

    public static LeaveRequestAvailableDaysDao leaveRequestAvailableDaysDaoConverter(LeaveRequestAvailableDays leaveRequestAvailableDays) {
        return LeaveRequestAvailableDaysDao
                .builder()
                .id(leaveRequestAvailableDays.getId())
                .type(leaveRequestAvailableDays.getType())
                .remaining(leaveRequestAvailableDays.getRemaining())
                .taken(leaveRequestAvailableDays.getTaken())
                .build();
    }
}
