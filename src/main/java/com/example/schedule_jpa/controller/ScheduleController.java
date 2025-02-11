package com.example.schedule_jpa.controller;

import com.example.schedule_jpa.Common.Const;
import com.example.schedule_jpa.dto.scheduleDto.ScheduleRequestDto;
import com.example.schedule_jpa.dto.scheduleDto.ScheduleResponseDto;
import com.example.schedule_jpa.dto.scheduleDto.UpdateScheduleRequestDto;
import com.example.schedule_jpa.entity.User;
import com.example.schedule_jpa.service.ScheduleService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/save")
    public ResponseEntity<ScheduleResponseDto> save(@Valid @RequestBody ScheduleRequestDto requestDto){
        ScheduleResponseDto save = scheduleService.save(requestDto.getUserId(), requestDto.getTitle(), requestDto.getContents());
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id){
        ScheduleResponseDto findById = scheduleService.findById(id);
        return new ResponseEntity<>(findById, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @Valid @RequestBody (required = false) UpdateScheduleRequestDto requestDto,
            HttpSession session
    ){
        ScheduleResponseDto updateSchedule = scheduleService.updateSchedule(id, requestDto.getTitle(), requestDto.getContents(), (User) session.getAttribute(Const.LOGIN_USER));
        return new ResponseEntity<>(updateSchedule, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id, HttpSession session){
        User loginUser = (User) session.getAttribute(Const.LOGIN_USER);
        scheduleService.deleteSchedule(id, loginUser.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
