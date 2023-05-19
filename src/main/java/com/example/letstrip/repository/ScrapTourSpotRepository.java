package com.example.letstrip.repository;

import com.example.letstrip.entity.ScrapTourSpot;
import com.example.letstrip.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScrapTourSpotRepository extends JpaRepository<ScrapTourSpot, Long> {
    List<ScrapTourSpot> findByUser(User user);
    ScrapTourSpot findByName(String name);
}
