package com.example.schedule_jpa.service;

import com.example.schedule_jpa.dto.userDto.UserResponseDto;
import com.example.schedule_jpa.entity.User;
import com.example.schedule_jpa.repository.UserRepository;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto save(String name, @Email String email, String password) {
        User user = new User(name, email, password);
        User save = userRepository.save(user);
        return new UserResponseDto(save.getId(), save.getUserName(), save.getEmail());
    }

    public UserResponseDto findById(Long id) {
        User user = userRepository.findByIdOrElseThrow(id);
        return new UserResponseDto(user.getId(), user.getUserName(), user.getEmail());
    }

    public UserResponseDto updateUser(Long id, String name, @Email String email, String password) {
        User findUser = userRepository.findByIdOrElseThrow(id);

        if(name != null){
            findUser.setUserName(name);
        }

        if(email != null){
            findUser.setEmail(email);
        }

        if (name == null && email == null || password == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if(!password.equals(findUser.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        userRepository.save(findUser);
        return new UserResponseDto(findUser.getId(), findUser.getUserName(), findUser.getEmail());
    }

    public void deleteUser(Long id, String password) {
        User findUser = userRepository.findByIdOrElseThrow(id);

        if(!findUser.getPassword().equals(password)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        userRepository.delete(findUser);
    }
}
