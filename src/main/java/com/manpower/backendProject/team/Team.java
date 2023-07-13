package com.manpower.backendProject.team;

import com.manpower.backendProject.user.User;
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
    private int id;
    @OneToOne(mappedBy = "teamManager")
    private User manager;
    @OneToMany(mappedBy = "team",fetch = FetchType.LAZY)
    private List<User> members;
}
