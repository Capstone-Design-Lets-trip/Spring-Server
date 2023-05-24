package com.example.letstrip.service;

import com.example.letstrip.entity.Survey;
import com.example.letstrip.entity.User;
import com.example.letstrip.repository.SurveyRepository;
import com.example.letstrip.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final UserRepository userRepository;

    @Transactional
    public Survey saveSurvey(String userId, Survey survey) {
        User user = userRepository.findById(userId).orElseThrow();
        survey.setUser(user);
        if (user != null) {
            Survey existed = surveyRepository.findByUserAndCity(user, survey.getCity());
            if (existed != null) {
                existed.setAquarium(survey.getAquarium());
                existed.setMountain_ocean(survey.getMountain_ocean());
                existed.setActivity_actrraction(survey.getActivity_actrraction());
                existed.setInside_outside(survey.getInside_outside());
                existed.setShopping(survey.getShopping());
                existed.setProperties(survey.getProperties());
                existed.setStartDate(survey.getStartDate());
                existed.setEndDate(survey.getEndDate());
                existed.setTravel_start(survey.getTravel_start());
                existed.setTravel_end(survey.getTravel_end());
                return existed;
            } else {
                return surveyRepository.save(survey);
            }
        } else {
            throw new RuntimeException("invalid user");
        }
    }

    public Survey findSurvey(String email, String city) {
        Survey survey = surveyRepository.findByEmailAndCity(email, city);
        if (survey != null) return survey;
        else {
            throw new RuntimeException("not exist survey");
        }
    }
}
