package com.example.SchoolOpdracht.SchoolOpdracht.repository;

import com.example.SchoolOpdracht.SchoolOpdracht.model.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findByDueDateBefore(Date datum);

    Optional<Task> findByIdAndCheckOverDue(Long id, Date datum);
}
