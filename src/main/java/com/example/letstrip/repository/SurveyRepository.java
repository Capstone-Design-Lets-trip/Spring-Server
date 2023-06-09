package com.example.letstrip.repository;

import com.example.letstrip.entity.Survey;
import com.example.letstrip.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {
    Survey findByUserAndCity(User user, String city);
    Survey findByEmailAndCity(String email, String city);
}
