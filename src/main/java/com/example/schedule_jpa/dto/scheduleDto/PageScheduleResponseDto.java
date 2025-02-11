package com.example.schedule_jpa.dto.scheduleDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class PageScheduleResponseDto {
    private final String title;
    private final String contents;
    private final Long commentCount;
    private final LocalDate createdDate;
    private final LocalDate updateDate;
    private final String userName;
}
