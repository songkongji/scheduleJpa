package com.example.schedule_jpa.repository;

import com.example.schedule_jpa.dto.commentDto.CommentResponseDto;
import com.example.schedule_jpa.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {


    List<Comment> findByScheduleId(Long scheduleId);
}
