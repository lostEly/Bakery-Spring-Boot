package com.test.bakery.DTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OrderInfoDTO {
    @NotEmpty(message = "login is mandatory")
    private String login;
}
