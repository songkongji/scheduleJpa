package com.example.schedule_jpa.service;

import com.example.schedule_jpa.Common.Const;
import com.example.schedule_jpa.config.PasswordEncoder;
import com.example.schedule_jpa.dto.userDto.UserResponseDto;
import com.example.schedule_jpa.entity.User;
import com.example.schedule_jpa.exception.EmailAlreadyExistException;
import com.example.schedule_jpa.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto save(String name, @Email String email, String password) {
        Optional<User> findUser = userRepository.findByEmail(email);

        if(findUser.isPresent()){
            throw new EmailAlreadyExistException("이미 사용 중인 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(name, email, encodedPassword);
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

    public UserResponseDto login(@Email String email, String password, HttpServletRequest request) {
        User findUser = userRepository.findByEmailOrElseThrow(email);

        if(findUser != null){   //find 할때 이미 이메일은 검증됨
            boolean matches = passwordEncoder.matches(password, findUser.getPassword());

            if(!matches){
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            }
        }

        HttpSession session = request.getSession();
        session.setAttribute(Const.LOGIN_USER, findUser);
        //findUser.getId()의 경고는 NPE 가능성 때문에 IDE에서 보내는 경고이다.
        //하지만 이미 위에서 findUser 변수는 레포지터리에서 null일 경우 오류 처리가 되기 때문에 경고를 신경 쓰지 않아도 된다.
        return new UserResponseDto(findUser.getId(), findUser.getUserName(), findUser.getEmail());
    }

    public void logout(HttpSession session) {
        if(session != null){
            session.invalidate();
        }
    }
}
