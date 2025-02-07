package com.example.schedule_jpa.controller;

import com.example.schedule_jpa.dto.ScheduleRequestDto;
import com.example.schedule_jpa.dto.ScheduleResponseDto;
import com.example.schedule_jpa.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/save")
    public ResponseEntity<ScheduleResponseDto> save(@RequestBody ScheduleRequestDto requestDto){
        ScheduleResponseDto save = scheduleService.save(requestDto.getUserName(), requestDto.getTitle(), requestDto.getContents());
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }
}
