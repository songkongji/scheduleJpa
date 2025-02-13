package com.example.schedule_jpa.exception;

import com.example.schedule_jpa.Common.ErrorType;
import lombok.Getter;


@Getter
public class CustomException extends RuntimeException{

    private final ErrorType errorType;

    public CustomException(String message, ErrorType errorType){
        super(message);
        this.errorType = errorType;
    }
}
