package com.manpower.backendProject.exception;

import com.manpower.backendProject.models.user.UserAlreadyExistsException;
import com.manpower.backendProject.models.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public Map<String, String> handleUserAlreadyExists(RuntimeException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage()); // TODO: response style 1
        return response;
        // TODO: decide on response style. We use 2 everywhere (except here) for now
        //return ResponseEntity.status(HttpStatus.CONFLICT).body(response); / TODO: response style 2
    }
}
