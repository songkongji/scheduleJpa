package com.example.schedule_jpa.Common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorType {
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED.name(),401, "로그인이 필요합니다");

    private final String status;
    private final int code;
    private final String message;

    ErrorType(String status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
