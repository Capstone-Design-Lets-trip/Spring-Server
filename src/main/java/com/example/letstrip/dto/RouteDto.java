package com.example.letstrip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteDto {

    private List<String> arrive_times;
    private List<String> depart_times;
    private List<Integer> move_times;
    private List<String> names;
}
