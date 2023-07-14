package com.manpower.backendProject.auth;

import com.manpower.backendProject.config.JwtService;
import com.manpower.backendProject.controllers.dao.UserDao;
import com.manpower.backendProject.token.Token;
import com.manpower.backendProject.token.TokenRepository;
import com.manpower.backendProject.token.TokenType;
import com.manpower.backendProject.user.User;
import com.manpower.backendProject.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = getUserTokenString(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userDao(
                        UserDao.buildUserDao(user)
                )

                .build();
    }

    private String getUserTokenString(User user) {
        var tmpToken = tokenRepository.findAllValidTokenByUser(user.getId()).stream().findFirst();
        if (tmpToken.isPresent()) {
            String token = tmpToken.get().getToken();
            if (jwtService.isTokenValid(token, user)) {
                return token;
            }
        }
        var token = jwtService.generateToken(user);
        saveUserToken(user, token);
        return token;
    }

    private Token generateToken() {
        return null;
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .userToken(user)
                .token(jwtToken)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

//    private void revokeAllUserTokens(User user) {
//        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
//        if (validUserTokens.isEmpty())
//            return;
//        validUserTokens.forEach(token -> {
//            token.setRevoked(true);
//        });
//        tokenRepository.saveAll(validUserTokens);
//    }
}
