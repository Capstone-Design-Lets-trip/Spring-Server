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
        if (user != null) {
            return surveyRepository.save(survey);
        } else {
            throw new RuntimeException("invalid user");
        }
    }
}
