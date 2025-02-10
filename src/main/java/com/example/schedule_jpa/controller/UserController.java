package com.example.schedule_jpa.controller;

import com.example.schedule_jpa.dto.userDto.UserRequestDto;
import com.example.schedule_jpa.dto.userDto.UserResponseDto;
import com.example.schedule_jpa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id){
        UserResponseDto findUser = userService.findById(id);
        return new ResponseEntity<>(findUser, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody (required = false) UserRequestDto requestDto){
        UserResponseDto updateUser = userService.updateUser(id, requestDto.getName(), requestDto.getEmail());
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
