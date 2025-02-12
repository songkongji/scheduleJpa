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

    @PostMapping    //댓글 생성. 세션 가져와서 생성한 유저 정보 등록 
    public ResponseEntity<CommentResponseDto> save(@Valid @RequestBody CommentRequestDto requestDto, HttpSession session){
        User user = (User) session.getAttribute(Const.LOGIN_USER);
        CommentResponseDto save = commentService.save(user.getId(), requestDto.getScheduleId(), requestDto.getContents());
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @GetMapping("/{scheduleId}")    //댓글 목록 조회. 일정 번호를 통해 해당 일정의 모든 댓글 조회
    public ResponseEntity<List<CommentResponseDto>> findByScheduleId(@PathVariable Long scheduleId){
        List<CommentResponseDto> findComment = commentService.findByScheduleId(scheduleId);
        return new ResponseEntity<>(findComment, HttpStatus.OK);
    }

    @PutMapping("/{id}")//댓글 수정. 세션 가져와서 본인의 댓글만 수정 가능
    public ResponseEntity<CommentResponseDto> updateComment(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCommentRequestDto requestDto,
            HttpSession session
    ){
        User user = (User) session.getAttribute(Const.LOGIN_USER);
        CommentResponseDto update = commentService.update(id, user.getId(), requestDto.getContents(), requestDto.getScheduleId());
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @DeleteMapping("/{id}") //댓글 삭제 세션 가져와서 본인의 댓글만 삭제 가능
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
