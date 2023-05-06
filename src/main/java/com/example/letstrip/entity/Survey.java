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

    private String insideOrOutside;
    private String mountainOrOcean;
    private String activityOrRest;
    private String aquarium;
    private String shopping;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalTime travelStart;
    private LocalTime travelEnd;

    @Column(columnDefinition = "LONGTEXT")
    private String properties;

}
