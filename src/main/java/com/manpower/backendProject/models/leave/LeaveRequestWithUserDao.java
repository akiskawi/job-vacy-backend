package com.manpower.backendProject.models.leave;

import com.manpower.backendProject.models.leave_types.LeaveType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequestWithUserDao {
    private long id;
    private long user_id;
    private String email;
    private LeaveType type;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate endDate;
    private LeaveRequestSTATUS status;

    public static LeaveRequestWithUserDao leaveRequestDaoConverter(LeaveRequest leaveRequest){
        return LeaveRequestWithUserDao
                .builder()
                .id(leaveRequest.getId())
                .user_id(leaveRequest.getUser().getId())
                .email(leaveRequest.getUser().getEmail())
                .type(leaveRequest.getType())
                .startDate(leaveRequest.getStartDate())
                .endDate(leaveRequest.getEndDate())
                .status(leaveRequest.getStatus())
                .build();
    }
}
