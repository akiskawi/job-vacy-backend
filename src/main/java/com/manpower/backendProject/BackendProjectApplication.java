package com.manpower.backendProject;

import com.manpower.backendProject.auth.AuthenticationResponse;
import com.manpower.backendProject.auth.AuthenticationService;
import com.manpower.backendProject.auth.RegisterRequest;
import com.manpower.backendProject.user.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.List;


@SpringBootApplication
public class BackendProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendProjectApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(
//            AuthenticationService service
//    ) {
//        return args -> {
//            var admin = RegisterRequest
//                    .builder()
//                    .firstname("Admin")
//                    .lastname("Admin")
//                    .email("admin@email.com")
//                    .password("1234")
//                    .roles(List.of(Role.ADMIN, Role.MANAGER, Role.USER))
//                    .build();
//            var manager = RegisterRequest
//                    .builder()
//                    .firstname("Manager")
//                    .lastname("Manager")
//                    .email("manager@email.com")
//                    .password("1234")
//                    .roles(List.of(Role.MANAGER, Role.USER))
//                    .build();
//            var user = RegisterRequest
//                    .builder()
//                    .firstname("user")
//                    .lastname("user")
//                    .email("user@email.com")
//                    .password("1234")
//                    .roles(List.of(Role.USER))
//                    .build();
//            var adminUser = RegisterRequest
//                    .builder()
//                    .firstname("Admin User")
//                    .lastname("Admin User")
//                    .email("auser@email.com")
//                    .password("1234")
//                    .roles(List.of(Role.ADMIN, Role.USER))
//                    .build();
//
//            var tokens = new HashMap<String, AuthenticationResponse>();
//            tokens.put("admin", service.register(admin));
//            tokens.put("manager", service.register(manager));
//            tokens.put("user", service.register(user));
//            tokens.put("adminUser", service.register(adminUser));
//
//            tokens.forEach((x, y) -> System.out.println(x + " = " + y));
//
//        };
//    }

}
