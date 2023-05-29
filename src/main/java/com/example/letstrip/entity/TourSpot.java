package com.example.letstrip.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TOUR_SPOT")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TourSpot {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "TOUR_SPOT_ID")
    private Long id;

    private double star;
    private double num;
    private String type;
    private String name;

    @Column(columnDefinition = "LONGTEXT")
    private String imgUrl;

    @Column(columnDefinition = "LONGTEXT")
    private String address;

    @Column(columnDefinition = "LONGTEXT")
    private String localAddress;

    @Column(columnDefinition = "LONGTEXT")
    private String internetAddress;

    @Column(columnDefinition = "LONGTEXT")
    private String overview;

    private String phoneNumber;
    private double rating;
    private String sun;
    private String mon;
    private String tue;
    private String wed;
    private String thu;
    private String fri;
    private String sat;
    private double latitude;
    private double longitude;
    private int cluster;
    private int avgTime;

    @Column(columnDefinition = "LONGTEXT")
    private String keywordImgUrl;
}
