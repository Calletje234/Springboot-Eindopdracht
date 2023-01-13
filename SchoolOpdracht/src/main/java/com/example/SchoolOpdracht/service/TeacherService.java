package com.example.SchoolOpdracht.service;

import com.example.SchoolOpdracht.dto.TeacherDto;
import com.example.SchoolOpdracht.exceptions.RecordNotFoundException;
import com.example.SchoolOpdracht.helpers.Util;
import com.example.SchoolOpdracht.model.Teacher;
import com.example.SchoolOpdracht.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.ArrayList;

import static java.util.Objects.isNull;

@Service
public class TeacherService {

    private final TeacherRepository repos;

    // constructor injection (instead of @Autowired)
    public TeacherService(TeacherRepository r) {
        this.repos = r;
    }

    public Long createTeacher(TeacherDto teacherDto) {
        Teacher newTeacher = new Teacher();

        // map dto to entity
        newTeacher.setFirstName(teacherDto.firstName);
        newTeacher.setLastName(teacherDto.lastName);
        newTeacher.setTaskAmount(teacherDto.taskAmount);

        Teacher savedTeacher = repos.save(newTeacher);
        return savedTeacher.getTeacherId();
    }

    public Iterable<TeacherDto> getTeachers(){
        Iterable<Teacher> allTeacher = repos.findAll();
        ArrayList<TeacherDto> resultList = new ArrayList<>();
        for(Teacher t: allTeacher){
            resultList.add(getReturnTeacherDto(t));
        }
        return resultList;
    }

    public TeacherDto getTeacherById(Long id) {
        Util.checkId(id, repos);
        Teacher requestedTeacher = repos.findById(id).get();
        return getReturnTeacherDto(requestedTeacher);

    }

    public TeacherDto addTaskAmount(Long id, TeacherDto teacherDto) {
        Util.checkId(id, repos);
        Teacher requestedTeacher = repos.findById(id).get();
        requestedTeacher.setTaskAmount(teacherDto.taskAmount);
        repos.save(requestedTeacher);
        return getReturnTeacherDto(requestedTeacher);
    }

    public TeacherDto getReturnTeacherDto(Teacher changedModel) {
        TeacherDto requestDto = new TeacherDto();
        requestDto.firstName = changedModel.getFirstName();
        requestDto.lastName = changedModel.getLastName();
        requestDto.taskAmount = changedModel.getTaskAmount();
        return requestDto;
    }

}