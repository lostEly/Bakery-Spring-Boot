package com.test.bakery.security_controller;

import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class RegistrationRequest {

    @NotEmpty(message = "login is mandatory")
    private String login;

    @NotEmpty(message = "password is mandatory")
    private String password;

    @NotEmpty(message = "userName is mandatory")
    private String userName;

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "email is not valid")
    private String email;

    @NotEmpty(message = "userLastName is mandatory")
    private String userLastName;

}
