package com.example.schedule_jpa.repository;

import com.example.schedule_jpa.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    default Schedule findByIdOrElseThrow(Long id){
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    default Schedule updateSchedule(Long id, String title, String contents){
        Schedule findById = findByIdOrElseThrow(id);
        findById.setTitle(title);
        findById.setContents(contents);
        save(findById);
        return findById;
    }
}
