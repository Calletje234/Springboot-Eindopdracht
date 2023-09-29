package com.example.SchoolOpdracht.SchoolOpdracht.repository;

import com.example.SchoolOpdracht.SchoolOpdracht.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
