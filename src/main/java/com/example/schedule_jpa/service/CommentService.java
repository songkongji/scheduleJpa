package com.example.schedule_jpa.service;

import com.example.schedule_jpa.dto.commentDto.CommentResponseDto;
import com.example.schedule_jpa.entity.Comment;
import com.example.schedule_jpa.entity.Schedule;
import com.example.schedule_jpa.entity.User;
import com.example.schedule_jpa.repository.CommentRepository;
import com.example.schedule_jpa.repository.ScheduleRepository;
import com.example.schedule_jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(scheduleId); //여기서 검증 됌
        List<Comment> comments = commentRepository.findByScheduleId(schedule.getId());
        return comments.stream()
                .map(comment -> new CommentResponseDto(
                        comment.getId(),
                        comment.getContents(),
                        comment.getUser().getId(),
                        comment.getSchedule().getId()
                ))
                .collect(Collectors.toList());
    }
}
