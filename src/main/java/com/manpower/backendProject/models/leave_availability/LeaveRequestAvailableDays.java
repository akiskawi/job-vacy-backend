package com.manpower.backendProject.models.leave_availability;

import com.manpower.backendProject.models.leave.LeaveRequestTYPE;
import com.manpower.backendProject.models.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>A class that represents the available leave days for a user.</p>
 */
@Entity
@Table(name = "number_of_requests")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LeaveRequestAvailableDays {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private short taken;
    private short remaining;
    @Enumerated(EnumType.STRING)
    private LeaveRequestTYPE type;
}
