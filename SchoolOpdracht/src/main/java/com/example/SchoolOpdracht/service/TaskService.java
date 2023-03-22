package com.example.SchoolOpdracht.service;


import com.example.SchoolOpdracht.dto.ChildDto;
import com.example.SchoolOpdracht.dto.ParentDto;
import com.example.SchoolOpdracht.dto.TaskDto;
import com.example.SchoolOpdracht.exceptions.RecordNotFoundException;
import com.example.SchoolOpdracht.helpers.Util;
import com.example.SchoolOpdracht.model.Task;
import com.example.SchoolOpdracht.model.Teacher;
import com.example.SchoolOpdracht.model.Afwezig;
import com.example.SchoolOpdracht.model.Child;
import com.example.SchoolOpdracht.model.Parent;
import com.example.SchoolOpdracht.repository.ChildRepository;
import com.example.SchoolOpdracht.repository.ParentRepository;
import com.example.SchoolOpdracht.repository.TaskRepository;
import com.example.SchoolOpdracht.repository.TeacherRepository;

import net.bytebuddy.asm.Advice.Local;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Null;

@Service
public class TaskService {
    private final TaskRepository repos;
    private final TeacherRepository teacherRepos;
    private final ChildRepository childRepos;
    private final ParentRepository parentRepos;

    // constructor injection
    public TaskService(TaskRepository r, TeacherRepository t, ChildRepository c, ParentRepository p) {
        this.repos = r;
        this.teacherRepos = t;
        this.childRepos = c;
        this.parentRepos = p;
    }

    public Long createTask(TaskDto taskDto) {
        Task newTask = new Task();

        // map dto to entity
        newTask.setDueDate(taskDto.dueDate);
        newTask.setChild(getChildRepos(taskDto.childId));
        if(taskDto.teacherId != null) {
            newTask.setTeacher(getTeacherRepos(taskDto.teacherId));
        }

        Task savedTask = repos.save(newTask);
        
        return savedTask.getTaskId();
    }

    public Iterable<TaskDto> getAllTask() {
        Iterable<Task> allTasks = repos.findAll();
        ArrayList<TaskDto> resultList = new ArrayList<>();
        for(Task t : allTasks) {
            TaskDto newTaskDto = new TaskDto();
            newTaskDto.dueDate = t.getDueDate();
            resultList.add(newTaskDto);
        }
        return resultList;
    }

    public TaskDto getTaskById(Long id) {
        Util.checkId(id, repos);
        Task requestedTask = repos.findById(id).get();
        TaskDto requestedTaskDto = new TaskDto();
        requestedTaskDto.dueDate = requestedTask.getDueDate();
        return requestedTaskDto;
        }

    public TaskDto changeTaskStatus(Long taskId, TaskDto taskDto) {
        Task taskToChange = getTaskRepos(taskId);
        taskToChange.setStatus(taskDto.status);
        repos.save(taskToChange);
        return createReturnDto(taskToChange);
    }

    public TaskDto changeDueDate(Long taskId, TaskDto taskDto) {
        Task taskToChange = getTaskRepos(taskId);
        taskToChange.setDueDate(taskDto.dueDate);
        repos.save(taskToChange);
        return createReturnDto(taskToChange);
    }

    public TaskDto changeAssignedTeacher(Long taskId, TaskDto taskDto) {
        Task taskToChange = getTaskRepos(taskId);
        taskToChange.setTeacher(getTeacherRepos(taskDto.teacherId));
        repos.save(taskToChange);
        return createReturnDto(taskToChange);
    }

    public TaskDto deleteTaskById(Long taskId) {
        Task deletedTask = getTaskRepos(taskId);
        repos.deleteById(taskId);
        return createReturnDto(deletedTask);
    }

    public ChildDto getChildInformation(Long taskId) {
        Task requestedTask = getTaskRepos(taskId);
        return createChildReturnDto(getChildRepos(requestedTask.getChildId()));
    }

    public ParentDto getParentOfTaskChild(Long taskId) {
        Task requestedTask = getTaskRepos(taskId);
        return createParentReturnDto(requestedTask.getChild().getParent());
    }

    public TaskDto createReturnDto(Task changedModel) {
        TaskDto requestedDto = new TaskDto();
        requestedDto.dueDate = changedModel.getDueDate();;
        requestedDto.status = changedModel.getStatus();
        return requestedDto;
    }

    public ParentDto createParentReturnDto(Parent parentModel) {
        ParentDto parentDto = new ParentDto();
        parentDto.firstName = parentModel.getFirstName();
        parentDto.lastName = parentModel.getLastName();
        parentDto.address = parentModel.getAddress();
        parentDto.countryOfOrigin = parentModel.getCountryOfOrigin();
        parentDto.spokenLanguage = parentModel.getSpokenLanguage();
        parentDto.phoneNumber = parentModel.getPhoneNumber();
        parentDto.childList = parentModel.getChildren();
        return parentDto;
    }

    public ChildDto createChildReturnDto(Child childModel) {
        ChildDto childDto = new ChildDto();
        childDto.firstName = childModel.getFirstName();
        childDto.lastName = childModel.getLastName();
        childDto.dob = childModel.getDob();
        childDto.address = childModel.getAddress();
        childDto.startingDate = childModel.getStartingDate();
        childDto.countryOfOrigin = childModel.getCountryOfOrigin();
        childDto.spokenLanguage = childModel.getSpokenLanguage();
        childDto.Allergies = childModel.getAllergies();
        childDto.parentId = childModel.getParent().getParentId();
        return childDto;
    }

    public Boolean checkIfTeacherIValid(Long teacherId, LocalDate dueDate) {
        Teacher teacherToAssign = getTeacherRepos(teacherId);
        List<Afwezig> teacherAfwezigList = teacherToAssign.getAfwezigheid();
        for (Afwezig afwezigList : teacherAfwezigList) {
            if (dueDate.isAfter(afwezigList.getStartDate()) && dueDate.isBefore(afwezigList.getEndDate())) {
                return false;
            }
        }
        return true;
    }

    public Task getTaskRepos(Long id) {
        Util.checkId(id, repos);
        return repos.findById(id).get();
    }

    public Teacher getTeacherRepos(Long id) {
        Util.checkId(id, teacherRepos);
        return teacherRepos.findById(id).get();
    }

    public Child getChildRepos(Long id) {
        Util.checkId(id, childRepos);
        return childRepos.findById(id).get();
    }

    public Parent getParentRepos(Long id) {
        Util.checkId(id, parentRepos);
        return parentRepos.findById(id).get();
    }
}
