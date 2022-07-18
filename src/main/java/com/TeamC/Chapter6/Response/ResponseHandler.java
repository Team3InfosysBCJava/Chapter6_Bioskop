package com.TeamC.Chapter6.Response;

import com.TeamC.Chapter6.Model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(String s, HttpStatus ok, List<User> result) {
        return null;
    }
}
