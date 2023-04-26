package com.example.letstrip.service;

import com.example.letstrip.entity.TourSpot;
import com.example.letstrip.repository.TourSpotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TourSpotService {

    private final TourSpotRepository tourSpotRepository;

    public List<TourSpot> getTourSpot(List<String> names) {
        List<TourSpot> tourSpots = new ArrayList<>();
        for (String name : names) {
            tourSpots.add(tourSpotRepository.findByName(name));
        }

        return tourSpots;
    }
}
