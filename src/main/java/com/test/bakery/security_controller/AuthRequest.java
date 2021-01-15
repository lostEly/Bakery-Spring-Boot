package com.test.bakery.security_controller;
import lombok.Data;

@Data
public class AuthRequest {
    private String login;
    private String password;
}
