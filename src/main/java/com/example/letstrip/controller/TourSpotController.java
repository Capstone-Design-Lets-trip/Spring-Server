package com.example.letstrip.controller;

import com.example.letstrip.dto.ResponseDto;
import com.example.letstrip.dto.RouteDto;
import com.example.letstrip.dto.TourSpotDto;
import com.example.letstrip.entity.TourSpot;
import com.example.letstrip.service.TourSpotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/tour")
public class TourSpotController {

    private final TourSpotService tourSpotService;

    @GetMapping("/course")
    public ResponseEntity<?> getRoute(@RequestBody List<RouteDto> routeDtoList) {
        try {
            List<List<TourSpotDto>> responseDto = new ArrayList<>();
            for (int i = 0; i < routeDtoList.size(); i++) {
                List<TourSpot> spots = tourSpotService.getTourSpot(routeDtoList.get(i).getNames());

                List<TourSpotDto> dayCourse = new ArrayList<>();
                int idx = 0;
                for (TourSpot spot : spots) {
                    TourSpotDto dto = TourSpotDto.builder()
                            .id(spot.getId())
                            .name(spot.getName())
                            .imgUrl(spot.getImgUrl())
                            .address(spot.getAddress())
                            .localAddress(spot.getLocalAddress())
                            .phoneNumber(spot.getPhoneNumber())
                            .sun(spot.getSun())
                            .mon(spot.getMon())
                            .tue(spot.getTue())
                            .wed(spot.getWed())
                            .thu(spot.getThu())
                            .fri(spot.getFri())
                            .sat(spot.getSat())
                            .overview(spot.getOverview())
                            .latitude(spot.getLatitude())
                            .longitude(spot.getLongitude())
                            .arrive_time(routeDtoList.get(i).getArrive_times().get(idx))
                            .depart_time(routeDtoList.get(i).getDepart_times().get(idx))
                            .move_time(routeDtoList.get(i).getMove_times().get(idx))
                            .build();
                    dayCourse.add(dto);
                    idx++;
                }
                responseDto.add(dayCourse);
            }

            return ResponseEntity.ok().body(responseDto);
        } catch (Exception e) {
            ResponseDto responseDto = ResponseDto.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDto);
        }
    }
}
