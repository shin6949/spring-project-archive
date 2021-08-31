package com.cocoblue.securitytest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad Type Provided")
public class TypeError extends RuntimeException {
    public TypeError() {
        super("");
    }
}
