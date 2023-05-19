package com.example.letstrip.repository;

import com.example.letstrip.entity.ScrapTourSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrapTourSpotRepository extends JpaRepository<ScrapTourSpot, Long> {
}
