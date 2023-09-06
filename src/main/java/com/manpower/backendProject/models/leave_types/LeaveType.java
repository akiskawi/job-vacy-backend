package com.manpower.backendProject.models.leave_types;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class LeaveType {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String description;
    private boolean hasDayLimit;
    private boolean requiresDocuments;
}
