package com.hello.post.service;

import com.hello.post.dto.user.SignupDto;
import com.hello.post.entity.User;
import com.hello.post.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    ;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void signup(SignupDto signupDto) {
        String username = signupDto.getUsername();
        String password = passwordEncoder.encode(signupDto.getPassword());

        //회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if(checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        User user = new User(username, password);
        userRepository.save(user);
    }
}
