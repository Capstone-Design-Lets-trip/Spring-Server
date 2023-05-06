package com.example.letstrip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScrapDto {

    private String idList;
    private String arriveTimes;
    private String departTimes;
    private String moveTimes;
}
