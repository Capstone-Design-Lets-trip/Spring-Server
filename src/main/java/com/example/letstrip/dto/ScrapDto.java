package com.example.letstrip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScrapDto {

    private Long id;
    private List<TourSpotDto> tourSpotDtoList = new ArrayList<>();
}
