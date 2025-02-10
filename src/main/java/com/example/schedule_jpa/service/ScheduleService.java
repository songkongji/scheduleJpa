package com.example.schedule_jpa.service;

import com.example.schedule_jpa.dto.scheduleDto.ScheduleResponseDto;
import com.example.schedule_jpa.entity.Schedule;
import com.example.schedule_jpa.entity.User;
import com.example.schedule_jpa.repository.ScheduleRepository;
import com.example.schedule_jpa.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public ScheduleResponseDto save(Long userId, String title, String contents) {
        User findUser = userRepository.findByIdOrElseThrow(userId);
        Schedule schedule = new Schedule(title, contents);
        schedule.setUser(findUser);
        Schedule save = scheduleRepository.save(schedule);
        return new ScheduleResponseDto(save.getId(), save.getTitle(), save.getContents(), save.getUser().getId());
    }

    public ScheduleResponseDto findById(Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        return new ScheduleResponseDto(findSchedule.getId(), findSchedule.getTitle(), findSchedule.getContents(), findSchedule.getUser().getId());
    }

    public ScheduleResponseDto updateSchedule(Long id, String title, String contents) {
        Schedule schedule = scheduleRepository.updateSchedule(id, title, contents);
        return new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getContents(), schedule.getUser().getId());
    }

    public void deleteSchedule(Long id) {
        Schedule findById = scheduleRepository.findByIdOrElseThrow(id);
        scheduleRepository.delete(findById);
    }
}
