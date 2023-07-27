package com.manpower.backendProject.models.team;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.manpower.backendProject.models.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "teams")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Team {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    @JsonIgnoreProperties({"teamManager", "team"})
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    @ToString.Exclude
    private User manager;
    @OneToMany(mappedBy = "team",fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"teamManager", "team"})
    @ToString.Exclude
    private List<User> members;
}
