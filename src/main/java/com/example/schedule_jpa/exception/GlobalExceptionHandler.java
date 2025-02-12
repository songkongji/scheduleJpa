package com.example.schedule_jpa.exception;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleEmailAlreadyExistsException(EmailAlreadyExistException ex, HttpServletRequest request){
        return ex.getMessage();
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException exception, HttpServletRequest request) {
        HashMap<String, Object> responseBody = new HashMap<>();
        responseBody.put("status", exception.getStatusCode());
        responseBody.put("message", exception.getReason());
        responseBody.put("timeStamp", LocalDateTime.now());
        responseBody.put("path", request.getRequestURI());
        return new ResponseEntity<>(responseBody, exception.getStatusCode());
    }
}
