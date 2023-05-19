package com.example.letstrip.controller;

import com.example.letstrip.dto.ResponseDto;
import com.example.letstrip.dto.ScrapTourSpotDto;
import com.example.letstrip.dto.TourSpotDto;
import com.example.letstrip.entity.ScrapTourSpot;
import com.example.letstrip.entity.TourSpot;
import com.example.letstrip.service.ScrapTourSpotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/scrap/spot")
public class ScrapTourSpotController {

    private final ScrapTourSpotService scrapTourSpotService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@AuthenticationPrincipal String userId,
                                  @RequestBody ScrapTourSpotDto scrapTourSpotDto) {
        try {
            ScrapTourSpot scrapTourSpot = scrapTourSpotService.save(userId, scrapTourSpotDto);
            if (scrapTourSpot != null) {
                TourSpot tourSpot = scrapTourSpotService.findTourSpot(scrapTourSpot.getName());
                TourSpotDto response = TourSpotDto.builder()
                        .name(tourSpot.getName())
                        .imgUrl(tourSpot.getImgUrl())
                        .address(tourSpot.getAddress())
                        .sun(tourSpot.getSun())
                        .mon(tourSpot.getMon())
                        .tue(tourSpot.getTue())
                        .wed(tourSpot.getWed())
                        .thu(tourSpot.getThu())
                        .fri(tourSpot.getFri())
                        .sat(tourSpot.getSat())
                        .overview(tourSpot.getOverview())
                        .build();
                return ResponseEntity.ok().body(response);
            } else {
                throw new RuntimeException("Fail to scrap tour spot");
            }
        } catch(Exception e) {
            ResponseDto responseDto = ResponseDto.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDto);
        }
    }
}
