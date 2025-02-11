package com.example.schedule_jpa.service;

import com.example.schedule_jpa.dto.scheduleDto.PageScheduleResponseDto;
import com.example.schedule_jpa.dto.scheduleDto.ScheduleResponseDto;
import com.example.schedule_jpa.entity.Schedule;
import com.example.schedule_jpa.entity.User;
import com.example.schedule_jpa.repository.ScheduleRepository;
import com.example.schedule_jpa.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

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

    public ScheduleResponseDto updateSchedule(Long id, String title, String contents, User user) {
        Long userId = user.getId();
        Schedule schedule = scheduleRepository.updateSchedule(id, title, contents, userId);
        return new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getContents(), schedule.getUser().getId());
    }

    public void deleteSchedule(Long id, Long userId) {
        Schedule findById = scheduleRepository.findByIdOrElseThrow(id);

        if(userId != findById.getUser().getId()){ //일정을 만든 본인만 해당 일정 삭제
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        scheduleRepository.delete(findById);
    }

    public Page<PageScheduleResponseDto> getSchedulePage(Pageable pageable) {
        Page<Schedule> schedulePage = scheduleRepository.findAll(pageable);
        return schedulePage.map(schedule -> new PageScheduleResponseDto(
                schedule.getTitle(),
                schedule.getContents(),
                (long) schedule.getComments().size(), //stream.count() 보다 이게 성능 좋다고 함
                schedule.getCreateDate().toLocalDate(),
                schedule.getUpdateDate().toLocalDate(),
                schedule.getUser().getUserName()
        ));
    }
}
