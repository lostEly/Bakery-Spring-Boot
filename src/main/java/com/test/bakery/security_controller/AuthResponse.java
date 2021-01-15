package com.test.bakery.security_controller;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String login;
    private String token;
}
