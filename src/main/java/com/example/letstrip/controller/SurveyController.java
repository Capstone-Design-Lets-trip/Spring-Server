package com.example.letstrip.controller;

import com.example.letstrip.dto.ResponseDto;
import com.example.letstrip.dto.SurveyDto;
import com.example.letstrip.entity.Survey;
import com.example.letstrip.service.SurveyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/survey")
public class SurveyController {

    private final SurveyService surveyService;

    @PostMapping("/save")
    public ResponseEntity<?> saveSurvey(@AuthenticationPrincipal String userId,
                                        @RequestBody SurveyDto surveyDto) {
        try {
            String properties = "";
            for (String p : surveyDto.getProperties()) {
                properties += (p + ",");
            }
            Survey survey = Survey.builder()
                    .insideOrOutside(surveyDto.getInsideOrOutside())
                    .mountainOrOcean(surveyDto.getMountainOrOcean())
                    .activityOrRest(surveyDto.getActivityOrRest())
                    .aquarium(surveyDto.getAquarium())
                    .shopping(surveyDto.getShopping())
                    .startDate(surveyDto.getStartDate())
                    .endDate(surveyDto.getEndDate())
                    .travelStart(surveyDto.getTravelStart())
                    .travelEnd(surveyDto.getTravelEnd())
                    .properties(properties)
                    .build();

            Survey savedSurvey = surveyService.saveSurvey(userId, survey);
            List<String> propertyList = Arrays.asList(savedSurvey.getProperties().split(","));

            SurveyDto responseDto = SurveyDto.builder()
                    .id(savedSurvey.getId())
                    .insideOrOutside(savedSurvey.getInsideOrOutside())
                    .mountainOrOcean(savedSurvey.getMountainOrOcean())
                    .activityOrRest(savedSurvey.getActivityOrRest())
                    .aquarium(savedSurvey.getAquarium())
                    .shopping(savedSurvey.getShopping())
                    .startDate(savedSurvey.getStartDate())
                    .endDate(savedSurvey.getEndDate())
                    .travelStart(savedSurvey.getTravelStart())
                    .travelEnd(savedSurvey.getTravelEnd())
                    .properties(propertyList)
                    .build();

            return ResponseEntity.ok().body(responseDto);

        } catch (Exception e) {
            ResponseDto responseDto = ResponseDto.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDto);
        }
    }
}
