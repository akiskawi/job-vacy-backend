package com.manpower.backendProject.models.token;

import com.manpower.backendProject.models.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token {

    @Id
    @GeneratedValue
    public long id;

    @Column(unique = true)
    public String token;

//    @Enumerated(EnumType.STRING)
//    public TokenType tokenType = TokenType.BEARER;

    public boolean revoked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;
}