package com.manpower.backendProject.mocks;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manpower.backendProject.models.auth.RegisterRequest;
import com.manpower.backendProject.models.user.Role;
import com.manpower.backendProject.models.user.UpdateUser;
import com.manpower.backendProject.models.user.User;
import com.manpower.backendProject.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomMocks {
    @Autowired
    private final UserRepository userRepository;
    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public static User mockUser() {
        return User.builder()
                .firstname("User")
                .lastname("Mock")
                .email("user.mock@email.com")
                .password(encoder.encode("1234"))
                .roles(List.of(Role.USER))
                .enabled(true)
                .build();
    }

    public User getPersistedMockUser() {
        return userRepository.findByEmail("user.mock@email.com")
                .orElseGet(() -> userRepository.save(mockUser()));
    }

    public static RegisterRequest mockRegisterRequest() {
        return RegisterRequest.builder()
                .firstname("User")
                .lastname("Mock")
                .email("user.mock@email.com")
                .password("1234")
                .roles(List.of(Role.USER))
                .build();
    }

    public static UpdateUser mockUpdateUser() {
        return UpdateUser.builder()
                .firstname("User")
                .lastname("Mock")
                .roles(List.of(Role.USER))
                .build();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
