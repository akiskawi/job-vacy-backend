package com.manpower.backendProject.models.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.manpower.backendProject.models.Timestamped;
import com.manpower.backendProject.models.leave_availability.LeaveRequestAvailableDays;
import com.manpower.backendProject.models.leave.LeaveRequest;
import com.manpower.backendProject.models.team.Team;
import com.manpower.backendProject.models.token.Token;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data()
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "_user")
@ToString(exclude = {"tokens","requests","team","remainingDays"})
public class User extends Timestamped implements UserDetails {
    @Id
    @GeneratedValue
    private long id;
    private String firstname;
    private String lastname;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private List<Role> roles;
    @OneToMany(mappedBy = "requestsUser")
    private List<LeaveRequest> requests;
    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;
    @OneToOne(mappedBy = "manager")
    private Team teamManager;
    @OneToMany(mappedBy = "usersLeaveRequestsRemainingDays")
    private List<LeaveRequestAvailableDays> remainingDays;
    private boolean enabled;
    @OneToMany(mappedBy = "tokensUser")
    private List<Token> tokens;





    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
