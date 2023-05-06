package com.example.letstrip.service;

import com.example.letstrip.dto.TourSpotDto;
import com.example.letstrip.entity.Scrap;
import com.example.letstrip.entity.User;
import com.example.letstrip.repository.ScrapRepository;
import com.example.letstrip.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScrapService {

    private final ScrapRepository scrapRepository;
    private final UserRepository userRepository;

    @Transactional
    public Scrap saveScrap(String userId, List<TourSpotDto> tourSpotDtoList) {
        User user = userRepository.findById(userId).orElseThrow();

        String ids = "";
        String arriveTimes = "";
        String departTimes = "";
        String moveTimes = "";

        for (TourSpotDto spot : tourSpotDtoList) {
            ids += (spot.getId() + " ");
            arriveTimes += (spot.getArrive_time() + " ");
            departTimes += (spot.getDepart_time() + " ");
            moveTimes += (spot.getMove_time() + " ");
        }

        Scrap scrap = Scrap.builder()
                .user(user)
                .idList(ids)
                .arriveTimes(arriveTimes)
                .departTimes(departTimes)
                .moveTimes(moveTimes)
                .build();

        scrapRepository.save(scrap);

        return scrap;
    }

    public List<Scrap> findAll(String userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return scrapRepository.findByUser(user);
    }
}
