package com.TeamC.Chapter6.Helper;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ExceptionHandler extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ExceptionHandler(String message) {
        super(message);
    }
}
