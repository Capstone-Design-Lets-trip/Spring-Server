package com.example.letstrip.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "SURVEY")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Survey {

    @Id
    @GeneratedValue
    @Column(name = "SURVEY_ID")
    private Long id;

    private String inside_outside;
    private String mountain_ocean;
    private String activity_actrraction;
    private String aquarium;
    private String shopping;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalTime travel_start;
    private LocalTime travel_end;

    @Column(columnDefinition = "LONGTEXT")
    private String properties;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public void setUser(User user) {
        this.user = user;
    }
}
