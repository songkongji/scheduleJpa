package com.example.schedule_jpa.controller;

import com.example.schedule_jpa.Common.Const;
import com.example.schedule_jpa.dto.commentDto.CommentRequestDto;
import com.example.schedule_jpa.dto.commentDto.CommentResponseDto;
import com.example.schedule_jpa.dto.commentDto.UpdateCommentRequestDto;
import com.example.schedule_jpa.entity.User;
import com.example.schedule_jpa.service.CommentService;
import jakarta.servlet.http.HttpSession;
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
    public ResponseEntity<CommentResponseDto> save(@Valid @RequestBody CommentRequestDto requestDto, HttpSession session){
        User user = (User) session.getAttribute(Const.LOGIN_USER);
        CommentResponseDto save = commentService.save(user.getId(), requestDto.getScheduleId(), requestDto.getContents());
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<List<CommentResponseDto>> findByScheduleId(@PathVariable Long scheduleId){
        List<CommentResponseDto> findComment = commentService.findByScheduleId(scheduleId);
        return new ResponseEntity<>(findComment, HttpStatus.OK);
    }

    @PutMapping("/{id}")//id는 댓글의 고유식별자이다. (일정 하나에 댓글 여러개 달면 해당 id로 무슨 댓글 수정할건지 찾음)
    public ResponseEntity<CommentResponseDto> updateComment(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCommentRequestDto requestDto,
            HttpSession session
    ){
        User user = (User) session.getAttribute(Const.LOGIN_USER);
        CommentResponseDto update = commentService.update(id, user.getId(), requestDto.getContents(), requestDto.getScheduleId());
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long id,
            @Valid @RequestParam Long scheduleId,
            HttpSession session
            ){
        User user = (User) session.getAttribute(Const.LOGIN_USER);
        commentService.delete(id, scheduleId, user.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
