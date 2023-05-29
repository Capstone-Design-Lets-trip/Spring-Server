package com.example.letstrip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScrapTourSpotDto {

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
    private String arrive_time;
    private String depart_time;
    private int move_time;
    private String keywordImgUrl;
    private double num;
    private double star;
    private String type;
}
