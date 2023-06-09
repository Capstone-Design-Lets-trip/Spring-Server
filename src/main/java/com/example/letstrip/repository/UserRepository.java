package com.example.letstrip.repository;

import com.example.letstrip.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByEmail(String email);
    User findByEmail(String email);
    User findByEmailAndPassword(String email, String password);
}
