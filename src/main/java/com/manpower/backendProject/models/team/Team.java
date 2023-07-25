package com.manpower.backendProject.models.team;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.manpower.backendProject.models.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private User manager;
    @OneToMany(mappedBy = "team",fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"teamManager", "team"})
    private List<User> members;
}
