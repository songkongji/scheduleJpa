package com.example.schedule_jpa.service;

import com.example.schedule_jpa.dto.commentDto.CommentResponseDto;
import com.example.schedule_jpa.entity.Comment;
import com.example.schedule_jpa.entity.Schedule;
import com.example.schedule_jpa.entity.User;
import com.example.schedule_jpa.repository.CommentRepository;
import com.example.schedule_jpa.repository.ScheduleRepository;
import com.example.schedule_jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    public CommentResponseDto save(Long userId, Long scheduleId, String contents) {
        Comment comment = new Comment(contents);
        User user = userRepository.findByIdOrElseThrow(userId);
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(scheduleId);
        comment.setUser(user);
        comment.setSchedule(schedule);
        Comment save = commentRepository.save(comment);
        return new CommentResponseDto(save.getId(), save.getContents(), save.getUser().getId(), save.getSchedule().getId());
    }

    public List<CommentResponseDto> findByScheduleId(Long scheduleId) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(scheduleId); //여기서 존재하는 일정인지 검증
        List<Comment> comments = commentRepository.findAllByScheduleId(schedule.getId()); //검증 된 id 값을 넣음
        return comments.stream()
                .map(comment -> new CommentResponseDto(
                        comment.getId(),
                        comment.getContents(),
                        comment.getUser().getId(),
                        comment.getSchedule().getId()
                ))
                .collect(Collectors.toList());  //comments의 각 요소를 List<responseDto> 에 대입하는 스트림 메서드
    }

    public CommentResponseDto update(Long id, Long userId, String contents, Long scheduleId) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(scheduleId);
        Comment comment = commentRepository.findByIdOrElseThrow(id);

        verifyUser(userId, comment.getUser().getId());
        validComment(comment.getSchedule().getId(), schedule.getId());

        comment.setContents(contents);
        commentRepository.save(comment);
        return new CommentResponseDto(comment.getId(), comment.getContents(), comment.getUser().getId(), comment.getSchedule().getId());
    }

    public void delete(Long id, Long scheduleId, Long userId) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(scheduleId);
        Comment comment = commentRepository.findByIdOrElseThrow(id);

        verifyUser(userId, comment.getUser().getId());
        validComment(comment.getSchedule().getId(), schedule.getId());

        commentRepository.delete(comment);
    }

    private void validComment(Long commentOnScheduleId, Long scheduleId){   //내가 댓글을 단 일정이 맞는가
        if(commentOnScheduleId != scheduleId){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    private void verifyUser(Long userId, Long userIdFromComment){  //해당 댓글을 작성한 유저가 맞는가?
        if(userId != userIdFromComment){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}
