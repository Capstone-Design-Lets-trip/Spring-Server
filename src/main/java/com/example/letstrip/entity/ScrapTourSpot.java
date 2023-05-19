package com.example.letstrip.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "SCRAP_TOUR_SPOT")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScrapTourSpot {

    @Id
    @GeneratedValue
    @Column(name = "SCRAP_TOUR_SPOT_ID")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    private String name;
}
