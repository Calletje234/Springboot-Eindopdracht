package com.example.SchoolOpdracht.SchoolOpdracht.repository;

import com.example.SchoolOpdracht.SchoolOpdracht.model.File;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FileRepository extends CrudRepository<File, Long> {
    List<File> findByParentIdAndParentType(String parentType, Long parentId);
}
