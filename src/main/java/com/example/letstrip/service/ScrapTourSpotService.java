package com.example.letstrip.service;

import com.example.letstrip.dto.ScrapTourSpotDto;
import com.example.letstrip.dto.TourSpotDto;
import com.example.letstrip.entity.Scrap;
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
        List<ScrapTourSpot> tourSpots = scrapTourSpotRepository.findByUser(user);
        boolean flag = false;
        for (int i = 0; i < tourSpots.size(); i++) {
            if (tourSpots.get(i).getName().equals(scrapTourSpotDto.getName())) {
                flag = true;
                break;
            }
        }
        if (flag) throw new RuntimeException("already exist");
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

    @Transactional
    public ScrapTourSpot deleteScrapTourSpot(String userId, String name) {
        User user = userRepository.findById(userId).orElseThrow();
        if (user != null) {
            ScrapTourSpot scrapTourSpot = scrapTourSpotRepository.findByName(name);
            if (scrapTourSpot.getUser() == user) {
                scrapTourSpotRepository.delete(scrapTourSpot);
                return scrapTourSpot;
            } else {
                throw new RuntimeException("Invalid user");
            }
        } else {
            throw new RuntimeException("Invalid user");
        }
    }
}
