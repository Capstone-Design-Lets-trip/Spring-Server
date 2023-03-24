package com.example.letstrip.auth;

import com.example.letstrip.auth.model.PostSignInReq;
import com.example.letstrip.auth.model.PostSignInRes;
import com.example.letstrip.auth.model.PostSignUpReq;
import com.example.letstrip.auth.model.PostSignUpRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody PostSignUpReq postSignUpReq) {
        try {
            User user = User.builder()
                    .email(postSignUpReq.getEmail())
                    .password(postSignUpReq.getPassword())
                    .name(postSignUpReq.getName())
                    .phoneNumber(postSignUpReq.getPhoneNumber())
                    .birth(postSignUpReq.getBirth())
                    .build();

            User registeredUser = userService.join(user);

            PostSignUpRes responseUser = PostSignUpRes.builder()
                    .email(registeredUser.getEmail())
                    .password(registeredUser.getPassword())
                    .name(registeredUser.getName())
                    .phoneNumber(registeredUser.getPhoneNumber())
                    .birth(registeredUser.getBirth())
                    .build();

            return ResponseEntity.ok().body(responseUser);
        } catch (Exception e) {
            throw new RuntimeException("회원가입에 실패했습니다.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> signInUser(@RequestBody PostSignInReq postSignInReq) {
        try {
            User user = User.builder()
                    .email(postSignInReq.getEmail())
                    .password(postSignInReq.getPassword())
                    .build();

            User signInUser = userService.signIn(user);

            PostSignInRes responseUser = PostSignInRes.builder()
                    .email(signInUser.getEmail())
                    .password(signInUser.getPassword())
                    .build();

            return ResponseEntity.ok().body(responseUser);

        } catch (Exception e) {
            throw new RuntimeException("로그인에 실패했습니다.");
        }
    }
}
