package com.example.letstrip.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SurveyDto {

    private Long id;
    private String inside_outside;
    private String mountain_ocean;
    private String activity_actrraction;
    private String aquarium;
    private String shopping;

    @JsonFormat(shape = JsonFormat.Shape.STRING,  pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING,  pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING,  pattern = "HH:mm")
    private LocalTime travel_start;

    @JsonFormat(shape = JsonFormat.Shape.STRING,  pattern = "HH:mm")
    private LocalTime travel_end;

    private List<String> properties;

    private String email;
}
