package com.example.schedule_jpa.controller;

import com.example.schedule_jpa.dto.commentDto.CommentRequestDto;
import com.example.schedule_jpa.dto.commentDto.CommentResponseDto;
import com.example.schedule_jpa.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> save(@Valid @RequestBody CommentRequestDto requestDto){
        CommentResponseDto save = commentService.save(requestDto.getUserId(), requestDto.getScheduleId(), requestDto.getContents());
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<List<CommentResponseDto>> findByScheduleId(@PathVariable Long scheduleId){
        List<CommentResponseDto> findComment = commentService.findByScheduleId(scheduleId);
        return new ResponseEntity<>(findComment, HttpStatus.OK);
    }

}
