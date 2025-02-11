package com.example.schedule_jpa.controller;

import com.example.schedule_jpa.dto.commentDto.CommentRequestDto;
import com.example.schedule_jpa.dto.commentDto.CommentResponseDto;
import com.example.schedule_jpa.dto.commentDto.UpdateCommentRequestDto;
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

    @PutMapping("/{id}")//id는 댓글의 고유식별자이다. (일정 하나에 댓글 여러개 달면 해당 id로 무슨 댓글 수정할건지 찾음)
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long id, @Valid @RequestBody UpdateCommentRequestDto requestDto){
        CommentResponseDto update = commentService.update(id, requestDto.getContents(), requestDto.getScheduleId());
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id, @Valid @RequestParam Long scheduleId){
        commentService.delete(id, scheduleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
