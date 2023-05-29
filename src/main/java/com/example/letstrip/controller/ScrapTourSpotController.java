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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/find")
    public ResponseEntity<?> find(@AuthenticationPrincipal String userId) {
        try {
            List<ScrapTourSpot> scrapTourSpotList = scrapTourSpotService.findScrapSpot(userId);
            List<ScrapTourSpotDto> response = new ArrayList<>();
            for (ScrapTourSpot spot : scrapTourSpotList) {
                TourSpot tourSpot = scrapTourSpotService.findTourSpot(spot.getName());
                ScrapTourSpotDto dto = ScrapTourSpotDto.builder()
                        .name(tourSpot.getName())
                        .imgUrl(tourSpot.getImgUrl())
                        .address(tourSpot.getAddress())
                        .localAddress(tourSpot.getLocalAddress())
                        .phoneNumber(tourSpot.getPhoneNumber())
                        .sun(tourSpot.getSun())
                        .mon(tourSpot.getMon())
                        .tue(tourSpot.getTue())
                        .wed(tourSpot.getWed())
                        .thu(tourSpot.getThu())
                        .fri(tourSpot.getFri())
                        .sat(tourSpot.getSat())
                        .overview(tourSpot.getOverview())
                        .latitude(tourSpot.getLatitude())
                        .longitude(tourSpot.getLongitude())
                        .keywordImgUrl(tourSpot.getKeywordImgUrl())
                        .num(tourSpot.getNum())
                        .star(tourSpot.getStar())
                        .type(tourSpot.getType())
                        .build();
                response.add(dto);
            }
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            ResponseDto responseDto = ResponseDto.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDto);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteScrapTourSpot(@AuthenticationPrincipal String userId,
                                                 @RequestBody ScrapTourSpotDto scrapTourSpotDto) {
        try {
            ScrapTourSpot deleted = scrapTourSpotService.deleteScrapTourSpot(userId, scrapTourSpotDto.getName());
            ScrapTourSpotDto response = ScrapTourSpotDto.builder()
                    .id(deleted.getId())
                    .name(deleted.getName())
                    .build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            ResponseDto responseDto = ResponseDto.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDto);
        }
    }
}
