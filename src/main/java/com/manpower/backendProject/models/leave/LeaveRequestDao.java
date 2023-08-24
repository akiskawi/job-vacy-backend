package com.manpower.backendProject.models.leave;

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
public class LeaveRequestDao {
    private long id;
    private LeaveRequestTYPE type;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate endDate;
    private LeaveRequestSTATUS status;

    public static LeaveRequestDao leaveRequestDaoConverter(LeaveRequest leaveRequest){
        return LeaveRequestDao
                .builder()
                .id(leaveRequest.getId())
                .type(leaveRequest.getType())
                .startDate(leaveRequest.getStartDate())
                .endDate(leaveRequest.getEndDate())
                .status(leaveRequest.getStatus())
                .build();
    }
}
