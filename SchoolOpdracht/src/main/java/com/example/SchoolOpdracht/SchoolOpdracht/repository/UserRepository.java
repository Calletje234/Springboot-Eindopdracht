package com.example.SchoolOpdracht.SchoolOpdracht.repository;

import com.example.SchoolOpdracht.SchoolOpdracht.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String Username);
}
