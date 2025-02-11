package com.example.schedule_jpa.dto.commentDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommentResponseDto {
    private final Long id;
    private final String contents;
    private final Long userId;
    private final Long scheduleId;
}
