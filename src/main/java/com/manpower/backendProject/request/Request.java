package com.manpower.backendProject.request;

import com.manpower.backendProject.user.User;
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
public class Request {
    @Id
    @GeneratedValue
    private int id;
    @Enumerated(EnumType.STRING)
    private RequestTYPE type;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)
    private RequestSTATUS status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userRequest;
}
