package com.hello.post.service;

import com.hello.post.dto.user.SignupDto;
import com.hello.post.entity.User;
import com.hello.post.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //회원가입
    public void signup(SignupDto signupDto) {
        String username = signupDto.getUsername();
        String password = signupDto.getPassword();
        String passwordCheck = signupDto.getCheckPassword();

        //닉네임과 같은 값이 비밀번호에 포함된 경우 회원가입 실패
        boolean usernameCheck = password.contains(username);
        if (usernameCheck) {
            throw new IllegalArgumentException("비밀번호에 유저네임을 포함할 수 없습니다.");
        }

        //비밀번호 일치여부 확인
        if(!password.equals(passwordCheck)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        //회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if(checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        String passwordEncode = passwordEncoder.encode(signupDto.getPassword());

        User user = new User(username, passwordEncode);
        userRepository.save(user);
    }
}
