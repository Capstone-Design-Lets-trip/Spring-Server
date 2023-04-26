package com.example.letstrip.controller;

import com.example.letstrip.dto.ResponseDto;
import com.example.letstrip.dto.RouteDto;
import com.example.letstrip.dto.TourSpotDto;
import com.example.letstrip.entity.TourSpot;
import com.example.letstrip.service.TourSpotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/tour")
public class TourSpotController {

    private final TourSpotService tourSpotService;

    @GetMapping("/route")
    public ResponseEntity<?> getRoute(@RequestBody RouteDto routeDto) {
        try {
            List<TourSpot> tourSpotList = tourSpotService.getTourSpot(routeDto.getNames());

            List<TourSpotDto> responseDtos = new ArrayList<>();
            int index = 0;
            for (TourSpot dto : tourSpotList) {
                TourSpotDto spot = TourSpotDto.builder()
                        .id(dto.getId())
                        .name(dto.getName())
                        .imgUrl(dto.getImgUrl())
                        .phoneNumber(dto.getPhoneNumber())
                        .address(dto.getAddress())
                        .localAddress(dto.getLocalAddress())
                        .sun(dto.getSun())
                        .mon(dto.getMon())
                        .tue(dto.getTue())
                        .wed(dto.getWed())
                        .thu(dto.getThu())
                        .fri(dto.getFri())
                        .sat(dto.getSat())
                        .overview(dto.getOverview())
                        .latitude(dto.getLatitude())
                        .longitude(dto.getLongitude())
                        .arrive_times(routeDto.getArrive_times().get(index).substring(0, 16))
                        .depart_times(routeDto.getDepart_times().get(index).substring(0, 16))
                        .move_times(routeDto.getMove_times().get(index))
                        .build();
                responseDtos.add(spot);
                index++;
            }

            return ResponseEntity.ok().body(responseDtos);
        } catch (Exception e) {
            ResponseDto responseDto = ResponseDto
                    .builder()
                    .error(e.getMessage())
                    .build();

            return ResponseEntity.badRequest().body(responseDto);
        }
    }
}
