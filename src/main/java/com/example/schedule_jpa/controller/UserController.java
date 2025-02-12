package com.example.schedule_jpa.controller;

import com.example.schedule_jpa.Common.Const;
import com.example.schedule_jpa.dto.userDto.UserRequestDto;
import com.example.schedule_jpa.dto.userDto.UserResponseDto;
import com.example.schedule_jpa.entity.User;
import com.example.schedule_jpa.groups.LoginGroup;
import com.example.schedule_jpa.groups.SaveGroup;
import com.example.schedule_jpa.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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

    @PostMapping("/save") //유저 생성
    public ResponseEntity<UserResponseDto> save(@Validated(SaveGroup.class) @RequestBody UserRequestDto requestUserDto){
        UserResponseDto save = userService.save(requestUserDto.getName(), requestUserDto.getEmail(), requestUserDto.getPassword());
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")// 유저 조회
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id){
        UserResponseDto findUser = userService.findById(id);
        return new ResponseEntity<>(findUser, HttpStatus.OK);
    }

    @PatchMapping("/update")// 유저 수정
    public ResponseEntity<UserResponseDto> updateUser(
            HttpSession session,
            @Valid @RequestBody (required = false) UserRequestDto requestDto
    ){
        User user = (User) session.getAttribute(Const.LOGIN_USER);
        UserResponseDto updateUser = userService.updateUser(user.getId(), requestDto.getName(), requestDto.getEmail(), requestDto.getPassword());
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @DeleteMapping("{id}")// 유저 삭제
    public ResponseEntity<Void> deleteUser(@PathVariable Long id, @RequestBody UserRequestDto requestDto){
        userService.deleteUser(id, requestDto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")// 로그인. 서비스 단계에 servletRequest 넘김
    public ResponseEntity<UserResponseDto> login(
            @Validated(LoginGroup.class) @ModelAttribute UserRequestDto requestDto,
            HttpServletRequest request
    ){
        UserResponseDto login = userService.login(requestDto.getEmail(), requestDto.getPassword(), request);
        return new ResponseEntity<>(login, HttpStatus.OK);
    }

    @PostMapping("/logout") // 로그 아웃. 세션이 존재하면 세션 정보를 서비스 단계에 넘김
    public ResponseEntity<Void> logout(HttpServletRequest request){
        userService.logout(request.getSession(false));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
