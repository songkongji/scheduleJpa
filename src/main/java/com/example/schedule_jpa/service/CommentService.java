package com.example.schedule_jpa.service;

import com.example.schedule_jpa.dto.commentDto.CommentResponseDto;
import com.example.schedule_jpa.entity.Comment;
import com.example.schedule_jpa.entity.Schedule;
import com.example.schedule_jpa.entity.User;
import com.example.schedule_jpa.repository.CommentRepository;
import com.example.schedule_jpa.repository.ScheduleRepository;
import com.example.schedule_jpa.repository.UserRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
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
}
