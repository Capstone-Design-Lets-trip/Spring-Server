package com.example.letstrip.dto;

import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String id;
    private String token;
    private String email;
    private String username;
    private String phoneNumber;
    private Date birth;
    private String password;
}
