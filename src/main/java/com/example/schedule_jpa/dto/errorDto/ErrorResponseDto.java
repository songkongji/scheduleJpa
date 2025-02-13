package com.example.schedule_jpa.dto.errorDto;

import lombok.Getter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter

public class ErrorResponseDto {

    private final String status;
    private final int code;
    private final String message;
    private final String Time;

    public ErrorResponseDto(String status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter =DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Time = formatter.format(now);
    }
}
