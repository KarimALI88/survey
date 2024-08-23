package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.GONE)
public class SurveyExpiredException extends RuntimeException {
    public SurveyExpiredException(String message) {
        super(message);
    }
}
