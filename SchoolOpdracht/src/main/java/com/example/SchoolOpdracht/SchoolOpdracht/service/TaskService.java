package com.example.SchoolOpdracht.SchoolOpdracht.service;


import com.example.SchoolOpdracht.SchoolOpdracht.dto.FileDto;
import com.example.SchoolOpdracht.SchoolOpdracht.repository.ChildRepository;
import com.example.SchoolOpdracht.SchoolOpdracht.repository.ParentRepository;
import com.example.SchoolOpdracht.SchoolOpdracht.repository.TaskRepository;
import com.example.SchoolOpdracht.SchoolOpdracht.repository.TeacherRepository;
import com.example.SchoolOpdracht.SchoolOpdracht.dto.ChildDto;
import com.example.SchoolOpdracht.SchoolOpdracht.dto.ParentDto;
import com.example.SchoolOpdracht.SchoolOpdracht.dto.TaskDto;
import com.example.SchoolOpdracht.SchoolOpdracht.helpers.Util;
import com.example.SchoolOpdracht.SchoolOpdracht.model.Task;
import com.example.SchoolOpdracht.SchoolOpdracht.model.Teacher;
import com.example.SchoolOpdracht.SchoolOpdracht.model.Afwezig;
import com.example.SchoolOpdracht.SchoolOpdracht.model.Child;
import com.example.SchoolOpdracht.SchoolOpdracht.model.Parent;


import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


@Service
public class TaskService {
    private final TaskRepository repos;
    private final TeacherRepository teacherRepos;
    private final ChildRepository childRepos;
    private final ParentRepository parentRepos;
    private final FileService fileService

    // constructor injection
    public TaskService(TaskRepository r, TeacherRepository t, ChildRepository c, ParentRepository p, FileService f) {
        this.repos = r;
        this.teacherRepos = t;
        this.childRepos = c;
        this.parentRepos = p;
        this.fileService = f;
    }

    public Long createTask(TaskDto taskDto) {
        Task newTask = new Task();
        LocalDate dueDate = taskDto.dueDate.plusWeeks(6);
        // map dto to entity
        newTask.setDueDate(dueDate);
        newTask.setChild(getChildRepos(taskDto.childId));
        newTask.setAssigned(taskDto.assigned);
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
        return createReturnDto(requestedTask);
    }

    public List<FileDto> getFilesByTask(Long id) {
        return fileService.getAssociatedFiled("Task", id);
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

    public Long changeAssignedTeacher(Long taskId, TaskDto taskDto) {
        Task taskToChange = getTaskRepos(taskId);
        taskToChange.setTeacher(getTeacherRepos(taskDto.teacherId));
        repos.save(taskToChange);
        return taskToChange.getTeacher().getTeacherId();
    }

    public TaskDto deleteTaskById(Long taskId) {
        Task deletedTask = getTaskRepos(taskId);
        repos.deleteById(taskId);
        return createReturnDto(deletedTask);
    }

    public ChildDto getChildInformation(Long taskId) {
        Task requestedTask = getTaskRepos(taskId);
        return createChildReturnDto(getChildRepos(requestedTask.getChild().getChildId()));
    }

    public ParentDto getParentOfTaskChild(Long taskId) {
        Task requestedTask = getTaskRepos(taskId);
        return createParentReturnDto(requestedTask.getChild().getParent());
    }

    public TaskDto createReturnDto(Task changedModel) {
        TaskDto requestedDto = new TaskDto();
        requestedDto.dueDate = changedModel.getDueDate();
        requestedDto.status = changedModel.getStatus();
        requestedDto.assigned = changedModel.getAssigned();
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

    public long getDayBeforeOverdue(Long id) {
        Task taskToCheck = getTaskRepos(id);
        LocalDate taskDueDate = taskToCheck.getDueDate();
        LocalDate todaysDate = LocalDate.now();
        return ChronoUnit.DAYS.between(taskDueDate, todaysDate);
    }

    public Boolean checkIfTaskIsOverdue(Long id) {
        Task taskToCheck = getTaskRepos(id);
        LocalDate tasksDueDate = taskToCheck.getDueDate();
        LocalDate todaysDate = LocalDate.now();
        return !tasksDueDate.isBefore(todaysDate);
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
