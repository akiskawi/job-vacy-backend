package com.manpower.backendProject.mocks;

import com.manpower.backendProject.models.user.Role;
import com.manpower.backendProject.models.user.User;
import com.manpower.backendProject.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@AllArgsConstructor

public class CustomMocks {

    public static void userSetup(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        var user = User.builder()
                .firstname("User")
                .lastname("Test")
                .email("user.test@email.com")
                .password(passwordEncoder.encode("1234"))
                .roles(List.of(Role.USER))
                .enabled(true)
                .build();
        userRepository.findByEmail("user.test@email.com")
                .orElseGet(() -> userRepository.save(user));
    }
}
