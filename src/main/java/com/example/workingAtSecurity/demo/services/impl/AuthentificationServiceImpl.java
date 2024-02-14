package com.example.workingAtSecurity.demo.services.impl;

import com.example.workingAtSecurity.demo.dto.JwtAuthentificationResponse;
import com.example.workingAtSecurity.demo.dto.RefreshTokenRequest;
import com.example.workingAtSecurity.demo.dto.SignInRequest;
import com.example.workingAtSecurity.demo.dto.SignUpRequest;
import com.example.workingAtSecurity.demo.model.User;
import com.example.workingAtSecurity.demo.model.enums.Role;
import com.example.workingAtSecurity.demo.repositories.UserRepository;
import com.example.workingAtSecurity.demo.services.AuthenticationService;
import com.example.workingAtSecurity.demo.services.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthentificationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public User signup(SignUpRequest signUpRequest){
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setRoles(Collections.singleton(Role.ROLE_USER));
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        return userRepository.save(user);
    }
    public JwtAuthentificationResponse signin(SignInRequest signInRequest) throws IllegalArgumentException{
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),
                signInRequest.getPassword()));

        var user = userRepository.findByEmail(signInRequest.getEmail());

        var jwt = jwtService.generateToken(user);

        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
        JwtAuthentificationResponse jwtAuthentificationResponse = new JwtAuthentificationResponse();

        jwtAuthentificationResponse.setToken(jwt);
        jwtAuthentificationResponse.setRefreshToken(refreshToken);
        return jwtAuthentificationResponse;

    }

    public JwtAuthentificationResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail);
        if(jwtService.isTokenValid(refreshTokenRequest.getToken(), user)){
            var jwt = jwtService.generateToken(user);

            JwtAuthentificationResponse jwtAuthentificationResponse = new JwtAuthentificationResponse();

            jwtAuthentificationResponse.setToken(jwt);
            jwtAuthentificationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthentificationResponse;



        }
        return null;
    }
}
