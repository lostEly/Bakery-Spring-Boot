package com.test.bakery.DTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class DeleteOTP {
        @NotEmpty(message = "login is mandatory")
        private String login;
        @NotEmpty(message = "productId is mandatory")
        private Long productId;
}
