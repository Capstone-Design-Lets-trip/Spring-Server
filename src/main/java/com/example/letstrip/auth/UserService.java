package com.example.letstrip.auth;

import com.example.letstrip.auth.model.PostSignUpReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User join(User user) {
        if (user == null || user.getEmail() == null) {
            throw new IllegalArgumentException("유효하지 않은 사용자 입니다.");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일 입니다.");
        }

        return userRepository.save(user);
    }

    public User signIn(User user) {
        if (!userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("존재하지 않는 사용자 입니다.");
        }

        return userRepository.findByEmail(user.getEmail());
    }
}
