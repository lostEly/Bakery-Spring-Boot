package com.test.bakery.security_controller;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RegistrationRequest {

    @NotEmpty
    private String login;

    @NotEmpty
    private String password;

    @NotEmpty
    private String userName;

    @NotEmpty
    private String email;

    @NotEmpty
    private String userLastName;

}
