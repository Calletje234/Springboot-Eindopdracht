package com.example.SchoolOpdracht.repository;

import com.example.SchoolOpdracht.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
