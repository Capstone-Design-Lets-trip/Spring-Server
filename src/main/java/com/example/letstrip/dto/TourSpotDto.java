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
public class TourSpotDto {

    private Long id;
    private String name;
    private String imgUrl;
    private String address;
    private String localAddress;
    private String phoneNumber;
    private String sun;
    private String mon;
    private String tue;
    private String wed;
    private String thu;
    private String fri;
    private String sat;
    private String overview;
    private double latitude;
    private double longitude;
    private String arrive_times;
    private String depart_times;
    private int move_times;
}
