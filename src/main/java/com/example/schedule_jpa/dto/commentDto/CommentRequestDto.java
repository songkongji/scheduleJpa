package com.example.schedule_jpa.dto.commentDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommentRequestDto {
    @NotBlank
    @Size(min = 1, max = 100)
    private final String contents;
    @NotBlank
    private final Long userId;
    @NotBlank
    private final Long scheduleId;
}
