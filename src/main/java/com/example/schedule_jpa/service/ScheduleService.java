package com.example.schedule_jpa.service;

import com.example.schedule_jpa.dto.ScheduleResponseDto;
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

    public ScheduleResponseDto save(String userName, String title, String contents) {
        User findUser = userRepository.findUserByUserNameOrElseThrow(userName);
        Schedule schedule = new Schedule(title, contents);
        schedule.setUserName(findUser.getUserName());
        Schedule save = scheduleRepository.save(schedule);
        return new ScheduleResponseDto(save.getId(), save.getTitle(), save.getContents(), save.getUserName());
    }
}
