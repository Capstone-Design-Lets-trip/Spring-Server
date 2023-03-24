package com.example.letstrip.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USERS")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private Date birth;

}
