package com.example.schedule_jpa.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomException.class);
    //쓰이지 않는 Handler임!!!!!!
    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void handleLoginException(CustomException ex, HttpServletRequest request){
        String email = request.getParameter("email");
        String clientIp = request.getRemoteAddr();
        logger.warn("로그인 실패 - 사용자 이메일 : {}, IP 주소 : {}, 사유 : {}", email, clientIp, ex.getMessage());
    }
}
