package com.example.schedule_jpa.dto.userDto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponseDto {
    private final Long id;
    private final String name;
    @Email
    private final String email;
}
