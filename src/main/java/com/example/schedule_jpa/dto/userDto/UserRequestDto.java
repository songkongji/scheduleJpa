package com.example.schedule_jpa.dto.userDto;

import com.example.schedule_jpa.groups.SaveGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRequestDto {
    @NotBlank(groups = SaveGroup.class)
    @Size(min = 2, max = 18, message = "이름은 두 글자 이상이거나 '김수한무 거북이와 두루미 삼천갑자 동방삭' 보다 짧거나 같아야 합니다.")
    private final String name;

    @Email (message = "유효한 이메일 형식이 아닙니다.")
    private final String email;

    @NotBlank
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "비밀번호는 최소 8자 이상이어야 하며, 영문자, 숫자 및 특수문자를 포함해야 합니다."
    )
    private final String password;
}
