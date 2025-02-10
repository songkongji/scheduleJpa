package com.example.schedule_jpa.dto.userDto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRequestDto {
    private final String name;
    @Email
    private final String email;
}
