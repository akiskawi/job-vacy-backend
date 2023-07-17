package com.manpower.backendProject.models.leave;

import com.manpower.backendProject.models.Timestamped;
import com.manpower.backendProject.models.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "requests")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LeaveRequest extends Timestamped {
    @Id
    @GeneratedValue
    private int id;
    @Enumerated(EnumType.STRING)
    private LeaveRequestTYPE type;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)
    private LeaveRequestSTATUS status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
