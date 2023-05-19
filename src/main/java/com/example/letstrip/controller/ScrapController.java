package com.example.letstrip.controller;

import com.example.letstrip.dto.ResponseDto;
import com.example.letstrip.dto.ScrapDto;
import com.example.letstrip.dto.TourSpotDto;
import com.example.letstrip.entity.Scrap;
import com.example.letstrip.entity.TourSpot;
import com.example.letstrip.service.ScrapService;
import com.example.letstrip.service.TourSpotService;
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
@RequestMapping("/scrap")
public class ScrapController {

    private final ScrapService scrapService;
    private final TourSpotService tourSpotService;

    @PostMapping("/save")
    public ResponseEntity<?> scrap(@AuthenticationPrincipal String userId,
                                   @RequestBody List<TourSpotDto> tourSpotDtoList) {
        try {
            Scrap scrap = scrapService.saveScrap(userId, tourSpotDtoList);
            if (scrap != null) return ResponseEntity.ok().body("Success!");
            else throw new Exception();
        } catch (Exception e) {
            ResponseDto responseDto = ResponseDto.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDto);
        }
    }

    @GetMapping("/find/all")
    public ResponseEntity<?> findScrap(@AuthenticationPrincipal String userId) {
        try {
            List<Scrap> scrapList = scrapService.findAll(userId);

            List<ScrapDto> scraped = new ArrayList<>();
            for (Scrap scrap : scrapList) {
                String[] scrapIds = scrap.getIdList().split(" ");
                String[] scrapArriveTimes = scrap.getArriveTimes().split(" ");
                String[] scrapDepartTimes = scrap.getDepartTimes().split(" ");
                String[] scrapMoveTimes = scrap.getMoveTimes().split(" ");
                List<TourSpotDto> dtos = new ArrayList<>();
                TourSpotDto dto;
                if (scrapArriveTimes.length % 2 == 1) {
                    for (int i = 0; i < scrapIds.length; i++) {
                        TourSpot tourSpot = tourSpotService.getTourSpotById(Long.parseLong(scrapIds[i]));
                        if (i == 0) {
                            dto = TourSpotDto.builder()
                                    .id(tourSpot.getId())
                                    .name(tourSpot.getName())
                                    .imgUrl(tourSpot.getImgUrl())
                                    .arrive_time(null)
                                    .depart_time(scrapDepartTimes[2 * i] + " " + scrapDepartTimes[2 * i + 1])
                                    .move_time(Integer.parseInt(scrapMoveTimes[i]))
                                    .latitude(tourSpot.getLatitude())
                                    .longitude(tourSpot.getLongitude())
                                    .build();
                            dtos.add(dto);
                        } else {
                            dto = TourSpotDto.builder()
                                    .id(tourSpot.getId())
                                    .name(tourSpot.getName())
                                    .imgUrl(tourSpot.getImgUrl())
                                    .arrive_time(scrapArriveTimes[2 * i - 1] + " " + scrapArriveTimes[2 * i])
                                    .depart_time(scrapDepartTimes[2 * i] + " " + scrapDepartTimes[2 * i + 1])
                                    .move_time(Integer.parseInt(scrapMoveTimes[i]))
                                    .latitude(tourSpot.getLatitude())
                                    .longitude(tourSpot.getLongitude())
                                    .build();
                            dtos.add(dto);
                        }
                    }
                } else {
                    for (int i = 0; i < scrapIds.length; i++) {
                        TourSpot tourSpot = tourSpotService.getTourSpotById(Long.parseLong(scrapIds[i]));
                        dto = TourSpotDto.builder()
                                .id(tourSpot.getId())
                                .name(tourSpot.getName())
                                .imgUrl(tourSpot.getImgUrl())
                                .arrive_time(scrapArriveTimes[2 * i] + " " + scrapArriveTimes[2 * i + 1])
                                .depart_time(scrapDepartTimes[2 * i] + " " + scrapDepartTimes[2 * i + 1])
                                .move_time(Integer.parseInt(scrapMoveTimes[i]))
                                .latitude(tourSpot.getLatitude())
                                .longitude(tourSpot.getLongitude())
                                .build();
                        dtos.add(dto);
                    }
                }
                ScrapDto scrapDto = ScrapDto.builder()
                        .id(scrap.getId())
                        .tourSpotDtoList(dtos)
                        .build();
                scraped.add(scrapDto);
            }

            return ResponseEntity.ok().body(scraped);
        } catch (Exception e) {
            ResponseDto responseDto = ResponseDto.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDto);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteScrap(@AuthenticationPrincipal String userId,
                                         @RequestBody ScrapDto scrapDto) {
        try {
            scrapService.deleteScrap(userId, scrapDto.getId());
            return ResponseEntity.ok().body("Delete successfully!");
        } catch (Exception e) {
            ResponseDto responseDto = ResponseDto.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDto);
        }
    }
}
