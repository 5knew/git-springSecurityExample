package com.example.workingAtSecurity.demo.dto;

import lombok.Data;

@Data
public class JwtAuthentificationResponse {

    private String token;
    private String refreshToken;
}
