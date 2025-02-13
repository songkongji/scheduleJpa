package com.example.schedule_jpa.exception;

import com.example.schedule_jpa.dto.errorDto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponseDto> customExceptionHandler(CustomException e){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(e.getErrorType().getStatus(), e.getErrorType().getCode(), e.getErrorType().getMessage());
        return new ResponseEntity<>(errorResponseDto,HttpStatus.UNAUTHORIZED);

    }
}
