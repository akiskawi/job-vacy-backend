package com.manpower.backendProject.services;

import com.manpower.backendProject.models.auth.AuthenticationRequest;
import com.manpower.backendProject.models.auth.AuthenticationResponse;
import com.manpower.backendProject.models.user.UserDao;
import com.manpower.backendProject.models.token.Token;
import com.manpower.backendProject.repositories.TokenRepository;
import com.manpower.backendProject.models.user.User;
import com.manpower.backendProject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
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
        //var jwtToken = jwtService.generateToken(user);
        //saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userDao(UserDao.userDaoConverter(user))
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
                .user(user)
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
