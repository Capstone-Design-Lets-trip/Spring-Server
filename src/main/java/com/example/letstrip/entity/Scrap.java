package com.example.letstrip.entity;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "SCRAP")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Scrap {

    @Id
    @GeneratedValue
    @Column(name = "SCRAP_ID")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    private String idList;
    private String arriveTimes;
    private String departTimes;
    private String moveTimes;
}
