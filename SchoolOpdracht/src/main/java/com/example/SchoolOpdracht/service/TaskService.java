package com.example.SchoolOpdracht.service;


import com.example.SchoolOpdracht.dto.TaskDto;
import com.example.SchoolOpdracht.exceptions.RecordNotFoundException;
import com.example.SchoolOpdracht.helpers.Util;
import com.example.SchoolOpdracht.model.Task;
import com.example.SchoolOpdracht.model.Teacher;
import com.example.SchoolOpdracht.model.Child;
import com.example.SchoolOpdracht.model.Parent;
import com.example.SchoolOpdracht.repository.ChildRepository;
import com.example.SchoolOpdracht.repository.ParentRepository;
import com.example.SchoolOpdracht.repository.TaskRepository;
import com.example.SchoolOpdracht.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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

    public Long createTask(TaskDto taskDto, Long childId, Long parentId, Long teacherId) {
        Task newTask = new Task();

        // map dto to entity
        newTask.setDueDate(taskDto.dueDate);

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

    public TaskDto changeAssignedTeacher(Long taskId, Long teacherId,TaskDto taskDto) {
        Task taskToChange = getTaskRepos(taskId);
        taskToChange.setTeacher(getTeacherRepos(teacherId));
        repos.save(taskToChange);
        return createReturnDto(taskToChange);
    }

    public TaskDto deleteTaskById(Long taskId) {
        Task deletedTask = getTaskRepos(taskId);
        repos.deleteById(taskId);
        return createReturnDto(deletedTask);
    }

    public TaskDto createReturnDto(Task changedModel) {
        TaskDto requestedDto = new TaskDto();
        requestedDto.dueDate = changedModel.getDueDate();;
        requestedDto.status = changedModel.getStatus();
        return requestedDto;
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
