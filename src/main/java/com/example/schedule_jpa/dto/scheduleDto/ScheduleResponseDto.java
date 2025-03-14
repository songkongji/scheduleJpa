package com.example.schedule_jpa.dto.scheduleDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
    private final Long id;
    private final String title;
    private final String contents;
    private final Long userId;
}
