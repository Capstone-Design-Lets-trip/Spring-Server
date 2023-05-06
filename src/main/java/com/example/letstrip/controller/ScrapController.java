package com.example.letstrip.controller;

import com.example.letstrip.dto.ResponseDto;
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

            List<List<TourSpotDto>> scraped = new ArrayList<>();
            for (Scrap scrap : scrapList) {
                String[] scrapIds = scrap.getIdList().split(" ");
                String[] scrapArriveTimes = scrap.getArriveTimes().split(" ");
                String[] scrapDepartTimes = scrap.getDepartTimes().split(" ");
                String[] scrapMoveTimes = scrap.getMoveTimes().split(" ");
                List<TourSpotDto> dtos = new ArrayList<>();
                for (int i = 0; i < scrapIds.length; i++) {
                    TourSpot tourSpot = tourSpotService.getTourSpotById(Long.parseLong(scrapIds[i]));
                    TourSpotDto dto = TourSpotDto.builder()
                            .id(tourSpot.getId())
                            .name(tourSpot.getName())
                            .imgUrl(tourSpot.getImgUrl())
                            .arrive_time(scrapArriveTimes[2 * i] + " " + scrapArriveTimes[2 * i + 1])
                            .depart_time(scrapDepartTimes[2 * i] + " " + scrapDepartTimes[2 * i + 1])
                            .move_time(Integer.parseInt(scrapMoveTimes[i]))
                            .build();
                    dtos.add(dto);
                }
                scraped.add(dtos);
            }

            return ResponseEntity.ok().body(scraped);
        } catch (Exception e) {
            ResponseDto responseDto = ResponseDto.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDto);
        }
    }
}
