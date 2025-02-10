package com.example.schedule_jpa.service;

import com.example.schedule_jpa.dto.userDto.UserResponseDto;
import com.example.schedule_jpa.entity.User;
import com.example.schedule_jpa.repository.UserRepository;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto save(String name, @Email String email) {
        User user = new User(name, email);
        User save = userRepository.save(user);
        return new UserResponseDto(save.getId(), save.getUserName(), save.getEmail());
    }
}
