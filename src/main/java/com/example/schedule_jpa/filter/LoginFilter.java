package com.example.schedule_jpa.filter;

import com.example.schedule_jpa.Common.Const;
import com.example.schedule_jpa.exception.CustomException;
import com.example.schedule_jpa.dto.errorDto.ErrorResponseDto;
import com.example.schedule_jpa.Common.ErrorType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

public class LoginFilter implements Filter {

    private static final String[] WHITE_LIST = {"/users/save", "/users/login"};

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            if (!isWhiteList(requestURI)) {
                HttpSession session = httpRequest.getSession(false);

                if (session == null || session.getAttribute(Const.LOGIN_USER) == null) {
                    throw new CustomException("로그인 하셈", ErrorType.UNAUTHORIZED);
                }
            }
            chain.doFilter(request, response);
        } catch (CustomException e) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.setContentType("application/json; charset=UTF-8");//프론트엔드를 위해 json 형식으로 컨텐트 타입 보내주자
            httpResponse.setCharacterEncoding("UTF-8");
            ErrorResponseDto errorResponseDto = new ErrorResponseDto(ErrorType.UNAUTHORIZED.getStatus(), ErrorType.UNAUTHORIZED.getCode(), ErrorType.UNAUTHORIZED.getMessage());
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule()); //Jackson이 로컬데이트타임 받게 하기
            httpResponse.getWriter().write(objectMapper.writeValueAsString(errorResponseDto));
//            httpResponse.getWriter().write(
//                    "{\n" +
//                            "\"status\" : \"UNAUTHORIZED\",\n" +
//                            "\"message\" : \"" + e.getMessage() + "\",\n" +
//                            "\"timeStamp\" : \"" + LocalDateTime.now() + "\",\n" +
//                            "\"path\" : \"" + requestURI + "\"\n" +
//                            "}"
//            );
        } catch (Exception e){
            httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpResponse.setContentType("text/plain; charset=UTF-8");
            httpResponse.setCharacterEncoding("UTF-8");
            httpResponse.getWriter().write("서버 오류가 발생");
        }
    }

    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
