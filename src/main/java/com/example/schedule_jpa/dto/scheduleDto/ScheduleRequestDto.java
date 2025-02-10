package com.example.schedule_jpa.dto.scheduleDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleRequestDto {
    private final Long userId;
    @NotBlank
    @Size(min = 1, max = 30)
    private final String title;
    @NotBlank
    @Size(min = 1)
    private final String contents;
}
