package com.manpower.backendProject.models.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    @NotBlank(message = "Please enter an email.")
    @Email(message = "Please enter a valid email.")
    private String email;
    @NotBlank(message = "Please enter a password.")
    private String password;
}
