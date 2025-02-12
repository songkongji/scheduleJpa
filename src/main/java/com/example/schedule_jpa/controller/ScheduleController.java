package com.example.schedule_jpa.controller;

import com.example.schedule_jpa.Common.Const;
import com.example.schedule_jpa.dto.scheduleDto.PageScheduleResponseDto;
import com.example.schedule_jpa.dto.scheduleDto.ScheduleRequestDto;
import com.example.schedule_jpa.dto.scheduleDto.ScheduleResponseDto;
import com.example.schedule_jpa.dto.scheduleDto.UpdateScheduleRequestDto;
import com.example.schedule_jpa.entity.User;
import com.example.schedule_jpa.service.ScheduleService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/save")//일정 생성. 세션 가져와서 일정을 생성한 유저 정보 등록
    public ResponseEntity<ScheduleResponseDto> save(@Valid @RequestBody ScheduleRequestDto requestDto, HttpSession session){
        User user = (User) session.getAttribute(Const.LOGIN_USER);
        ScheduleResponseDto save = scheduleService.save(user.getId(), requestDto.getTitle(), requestDto.getContents());
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @GetMapping("{id}")//일정 단건 조회.
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id){
        ScheduleResponseDto findById = scheduleService.findById(id);
        return new ResponseEntity<>(findById, HttpStatus.OK);
    }

    @PatchMapping("{id}")//일정 수정. 세션 가져와서 본인이 생성한 일정만 수정 가능
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @Valid @RequestBody (required = false) UpdateScheduleRequestDto requestDto,
            HttpSession session
    ){
        ScheduleResponseDto updateSchedule = scheduleService.updateSchedule(id, requestDto.getTitle(), requestDto.getContents(), (User) session.getAttribute(Const.LOGIN_USER));
        return new ResponseEntity<>(updateSchedule, HttpStatus.OK);
    }

    @DeleteMapping("{id}") //일정 삭제. 세션 가져와서 본인이 생성한 일정만 삭제 가능
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id, HttpSession session){
        User loginUser = (User) session.getAttribute(Const.LOGIN_USER);
        scheduleService.deleteSchedule(id, loginUser.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping// 일정 페이징. 페이지네이션해서 파라미터에따라 일정들을 원하는 페이지에 원하는 갯수만큼 요청
    public ResponseEntity<Page<PageScheduleResponseDto>> schedulePage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "updateDate"));
        Page<PageScheduleResponseDto> schedulePage = scheduleService.getSchedulePage(pageable);
        return new ResponseEntity<>(schedulePage, HttpStatus.OK);
    }
}
