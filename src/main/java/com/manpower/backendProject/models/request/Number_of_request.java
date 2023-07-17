package com.manpower.backendProject.models.request;

import com.manpower.backendProject.models.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "number_of_requests")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Number_of_request {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userNumberOfRequest;
    private int taken;
    private int remaining;
    @Enumerated(EnumType.STRING)
    private RequestTYPE type;
}
