package com.example.sample.repository;

import com.example.sample.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/***
 * This class represents the repository bean for User entity
 * It is responsible for performing all the database operations for user entity
 * It is an extension of JpaRespository
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
