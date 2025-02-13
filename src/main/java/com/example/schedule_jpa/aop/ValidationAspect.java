package com.example.schedule_jpa.aop;

import com.example.schedule_jpa.repository.ScheduleRepository;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidationAspect {// AOP 공통 로직 분리해보기
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Before("execution(* com.example.schedule_jpa.service.CommentService.*(..)) && args(scheduleId, ..)")
    public void validateScheduleExistence(Long scheduleId) {
        scheduleRepository.findByIdOrElseThrow(scheduleId);
    }
}
