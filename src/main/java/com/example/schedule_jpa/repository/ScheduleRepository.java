package com.example.schedule_jpa.repository;

import com.example.schedule_jpa.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    default Schedule findByIdOrElseThrow(Long id){
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    default Schedule updateSchedule(Long id, String title, String contents, Long userId){
        Schedule findById = findByIdOrElseThrow(id);

        if(!findById.getUser().getId().equals(userId)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        if(title != null){
            findById.setTitle(title);
        }

        if(contents != null) {
            findById.setContents(contents);
        }

        if(title == null && contents == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        save(findById);
        return findById;
    }
}
