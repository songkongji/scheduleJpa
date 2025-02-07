package com.example.schedule_jpa.controller;

import com.example.schedule_jpa.dto.UserRequestDto;
import com.example.schedule_jpa.dto.UserResponseDto;
import com.example.schedule_jpa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/save")
    public ResponseEntity<UserResponseDto> save(@RequestBody UserRequestDto requestUserDto){
        UserResponseDto save = userService.save(requestUserDto.getName(), requestUserDto.getEmail());
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }
}
