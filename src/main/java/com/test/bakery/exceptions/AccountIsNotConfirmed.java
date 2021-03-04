package com.test.bakery.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AccountIsNotConfirmed extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public AccountIsNotConfirmed(String message) {
        super(message);
    }
}