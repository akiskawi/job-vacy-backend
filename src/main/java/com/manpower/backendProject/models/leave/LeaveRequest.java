package com.manpower.backendProject.models.leave;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.manpower.backendProject.models.Timestamped;
import com.manpower.backendProject.models.leave_types.LeaveType;
import com.manpower.backendProject.models.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "requests")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LeaveRequest extends Timestamped {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name = "leave_type_id")
    private LeaveType type;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)
    private LeaveRequestSTATUS status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIncludeProperties({"email", "id"})
    private User user;
}
