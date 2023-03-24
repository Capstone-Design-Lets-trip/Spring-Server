package com.example.letstrip.auth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostSignUpRes {

    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private Date birth;
}
