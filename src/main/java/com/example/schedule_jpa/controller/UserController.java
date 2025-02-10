package com.example.schedule_jpa.controller;

import com.example.schedule_jpa.dto.userDto.UserRequestDto;
import com.example.schedule_jpa.dto.userDto.UserResponseDto;
import com.example.schedule_jpa.groups.LoginGroup;
import com.example.schedule_jpa.groups.SaveGroup;
import com.example.schedule_jpa.service.UserService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/save")
    public ResponseEntity<UserResponseDto> save(@Validated(SaveGroup.class) @RequestBody UserRequestDto requestUserDto){
        UserResponseDto save = userService.save(requestUserDto.getName(), requestUserDto.getEmail(), requestUserDto.getPassword());
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id){
        UserResponseDto findUser = userService.findById(id);
        return new ResponseEntity<>(findUser, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody (required = false) UserRequestDto requestDto
    ){
        UserResponseDto updateUser = userService.updateUser(id, requestDto.getName(), requestDto.getEmail(), requestDto.getPassword());
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id, @RequestBody UserRequestDto requestDto){
        userService.deleteUser(id, requestDto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(
            @Validated(LoginGroup.class) @ModelAttribute UserRequestDto requestDto,
            HttpServletRequest request
    ){
        UserResponseDto login = userService.login(requestDto.getEmail(), requestDto.getPassword(), request);
        return new ResponseEntity<>(login, HttpStatus.OK);
    }
}
