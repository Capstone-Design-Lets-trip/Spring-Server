package com.example.letstrip.controller;

import com.example.letstrip.dto.ResponseDto;
import com.example.letstrip.dto.SurveyDto;
import com.example.letstrip.entity.Survey;
import com.example.letstrip.service.SurveyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
                    .inside_outside(surveyDto.getInside_outside())
                    .mountain_ocean(surveyDto.getMountain_ocean())
                    .activity_actrraction(surveyDto.getActivity_actrraction())
                    .aquarium(surveyDto.getAquarium())
                    .shopping(surveyDto.getShopping())
                    .startDate(surveyDto.getStartDate())
                    .endDate(surveyDto.getEndDate())
                    .travel_start(surveyDto.getTravel_start())
                    .travel_end(surveyDto.getTravel_end())
                    .properties(properties)
                    .email(surveyDto.getEmail())
                    .city(surveyDto.getCity())
                    .build();

            Survey savedSurvey = surveyService.saveSurvey(userId, survey);
            List<String> propertyList = Arrays.asList(savedSurvey.getProperties().split(","));

            SurveyDto responseDto = SurveyDto.builder()
                    .id(savedSurvey.getId())
                    .inside_outside(savedSurvey.getInside_outside())
                    .mountain_ocean(savedSurvey.getMountain_ocean())
                    .activity_actrraction(savedSurvey.getActivity_actrraction())
                    .aquarium(savedSurvey.getAquarium())
                    .shopping(savedSurvey.getShopping())
                    .startDate(savedSurvey.getStartDate())
                    .endDate(savedSurvey.getEndDate())
                    .travel_start(savedSurvey.getTravel_start())
                    .travel_end(savedSurvey.getTravel_end())
                    .properties(propertyList)
                    .email(survey.getEmail())
                    .city(survey.getCity())
                    .build();

            return ResponseEntity.ok().body(responseDto);

        } catch (Exception e) {
            ResponseDto responseDto = ResponseDto.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDto);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getSurvey(@RequestParam("email") String email, @RequestParam("city") String city) {
        try {
            Survey survey = surveyService.findSurvey(email, city);

            List<String> propertyList = new ArrayList<>();
            for (String property : survey.getProperties().split(",")) {
                propertyList.add(property.trim());
            }
            SurveyDto response = SurveyDto.builder()
                    .inside_outside(survey.getInside_outside())
                    .mountain_ocean(survey.getMountain_ocean())
                    .activity_actrraction(survey.getActivity_actrraction())
                    .aquarium(survey.getAquarium())
                    .shopping(survey.getShopping())
                    .properties(propertyList)
                    .startDate(survey.getStartDate())
                    .endDate(survey.getEndDate())
                    .travel_start(survey.getTravel_start())
                    .travel_end(survey.getTravel_end())
                    .city(survey.getCity())
                    .email(survey.getEmail())
                    .build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            ResponseDto responseDto = ResponseDto.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDto);
        }
    }
}
