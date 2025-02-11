package com.example.schedule_jpa.controller;

import com.example.schedule_jpa.dto.commentDto.CommentRequestDto;
import com.example.schedule_jpa.dto.commentDto.CommentResponseDto;
import com.example.schedule_jpa.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> save(@RequestBody CommentRequestDto requestDto){
        CommentResponseDto save = commentService.save(requestDto.getUserId(), requestDto.getScheduleId(), requestDto.getContents());
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }
}
