package com.example.letstrip.controller;

import com.example.letstrip.dto.ResponseDto;
import com.example.letstrip.dto.TourSpotDto;
import com.example.letstrip.entity.Scrap;
import com.example.letstrip.service.ScrapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/scrap")
public class ScrapController {

    private final ScrapService scrapService;

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
}
