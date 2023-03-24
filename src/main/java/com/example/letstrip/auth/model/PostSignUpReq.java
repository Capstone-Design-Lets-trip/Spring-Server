package com.example.letstrip.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostSignUpReq {

    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private Date birth;
}
