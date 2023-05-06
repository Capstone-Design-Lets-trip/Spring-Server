package com.example.letstrip.repository;

import com.example.letstrip.entity.TourSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourSpotRepository extends JpaRepository<TourSpot, String> {
    TourSpot findByName(String name);
    TourSpot findById(int id);
}
