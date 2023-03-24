package com.example.letstrip.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostSignInReq {

    private String email;
    private String password;
}
