package com.example.SchoolOpdracht.repository;

import com.example.SchoolOpdracht.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
