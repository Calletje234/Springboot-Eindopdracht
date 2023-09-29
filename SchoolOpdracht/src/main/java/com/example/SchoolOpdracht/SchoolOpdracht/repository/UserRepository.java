package com.example.SchoolOpdracht.SchoolOpdracht.repository;

import com.example.SchoolOpdracht.SchoolOpdracht.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
