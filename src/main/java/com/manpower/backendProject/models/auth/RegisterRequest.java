package com.manpower.backendProject.models.auth;

import com.manpower.backendProject.models.user.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstname;
    private String lastname;
    @NotBlank(message = "Please enter an email.")
    @Email(message = "Please enter a valid email.")
    private String email;
    @NotBlank(message = "Please enter a password.")
    private String password;
    private List<Role> roles;
}

