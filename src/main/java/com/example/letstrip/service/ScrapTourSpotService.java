package com.example.letstrip.service;

import com.example.letstrip.dto.ScrapTourSpotDto;
import com.example.letstrip.dto.TourSpotDto;
import com.example.letstrip.entity.ScrapTourSpot;
import com.example.letstrip.entity.TourSpot;
import com.example.letstrip.entity.User;
import com.example.letstrip.repository.ScrapTourSpotRepository;
import com.example.letstrip.repository.TourSpotRepository;
import com.example.letstrip.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScrapTourSpotService {

    private final UserRepository userRepository;
    private final ScrapTourSpotRepository scrapTourSpotRepository;
    private final TourSpotRepository tourSpotRepository;

    @Transactional
    public ScrapTourSpot save(String userId, ScrapTourSpotDto scrapTourSpotDto) {
        User user = userRepository.findById(userId).orElseThrow();
        ScrapTourSpot scrapTourSpot = ScrapTourSpot.builder()
                .name(scrapTourSpotDto.getName())
                .user(user)
                .build();
        return scrapTourSpotRepository.save(scrapTourSpot);
    }

    public TourSpot findTourSpot(String name) {
        return tourSpotRepository.findByName(name);
    }

    public List<ScrapTourSpot> findScrapSpot(String userId) {
        User user = userRepository.findById(userId).orElseThrow();
        if (user != null) {
            return scrapTourSpotRepository.findByUser(user);
        } else {
            throw new RuntimeException("Invalid user");
        }
    }
}
