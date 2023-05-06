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
    private String insideOrOutside;
    private String mountainOrOcean;
    private String activityOrRest;
    private String aquarium;
    private String shopping;

    @JsonFormat(shape = JsonFormat.Shape.STRING,  pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING,  pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING,  pattern = "HH:mm")
    private LocalTime travelStart;

    @JsonFormat(shape = JsonFormat.Shape.STRING,  pattern = "HH:mm")
    private LocalTime travelEnd;

    private List<String> properties;
}
