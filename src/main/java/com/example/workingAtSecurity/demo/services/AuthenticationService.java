package com.example.workingAtSecurity.demo.services;

import com.example.workingAtSecurity.demo.dto.JwtAuthentificationResponse;
import com.example.workingAtSecurity.demo.dto.RefreshTokenRequest;
import com.example.workingAtSecurity.demo.dto.SignInRequest;
import com.example.workingAtSecurity.demo.dto.SignUpRequest;
import com.example.workingAtSecurity.demo.model.User;

public interface AuthenticationService {

    User signup(SignUpRequest signUpRequest);
    JwtAuthentificationResponse signin(SignInRequest signInRequest);
    JwtAuthentificationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
