package com.example.SchoolOpdracht.service;


import com.example.SchoolOpdracht.dto.TaskDto;
import com.example.SchoolOpdracht.exceptions.RecordNotFoundException;
import com.example.SchoolOpdracht.model.Task;
import com.example.SchoolOpdracht.repository.TaskRepository;
import com.example.SchoolOpdracht.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TaskService {
    private final TaskRepository repos;
    private final TeacherRepository teacherRepos;

    // constructor injection
    public TaskService(TaskRepository r, TeacherRepository t) {
        this.repos = r;
        this.teacherRepos = t;
    }

    public Long createTask(TaskDto taskDto) {
        Task newTask = new Task();

        // map dto to entity
        newTask.setChildId(taskDto.childId);
        newTask.setParentId(taskDto.parentId);
        newTask.setDueDate(taskDto.dueDate);

        Task savedTask = repos.save(newTask);
        return savedTask.getTaskId();
    }

    public Iterable<TaskDto> getAllTask() {
        Iterable<Task> allTasks = repos.findAll();
        ArrayList<TaskDto> resultList = new ArrayList<>();
        for(Task t : allTasks) {
            TaskDto newTaskDto = new TaskDto();
            newTaskDto.childId = t.getChildId();
            newTaskDto.dueDate = t.getDueDate();
            newTaskDto.parentId = t.getParentId();
            newTaskDto.teacherId = t.getTeacherId();
            resultList.add(newTaskDto);
        }
        return resultList;
    }

    public TaskDto getTaskById(Long id) {
        checkId(id);
        Task requestedTask = repos.findById(id).get();
        TaskDto requestedTaskDto = new TaskDto();
        requestedTaskDto.childId = requestedTask.getChildId();
        requestedTaskDto.parentId = requestedTask.getParentId();
        requestedTaskDto.dueDate = requestedTask.getDueDate();
        requestedTaskDto.teacherId = requestedTask.getTeacherId();
        return requestedTaskDto;
        }

    public void changeTaskStatus(Long id, TaskDto taskDto) {
        checkId(id);
        Task taskToChange = repos.findById(id).get();
        taskToChange.setStatus(taskDto.status);
    }

    public void changeDueDate(Long id, TaskDto taskDto) {
        checkId(id);
        Task taskToChange = repos.findById(id).get();
        taskToChange.setDueDate(taskDto.dueDate);
    }

    public void changeAssignedTeacher(Long taskId, TaskDto taskDto) {
        checkId(taskId);
        Task taskToChange = repos.findById(taskId).get();
        taskToChange.setTeacherId(taskDto.teacherId);
        repos.save(taskToChange);
    }

    public void changeParentId(Long taskId, TaskDto taskDto) {
        checkId(taskId);
        Task taskToChange = repos.findById(taskId).get();
        taskToChange.setParentId(taskDto.parentId);
        repos.save(taskToChange);
    }

    public void checkId(Long id) {
        if(id < 0) {
            throw new IndexOutOfBoundsException("Id is not allowed to be negative");
        } else if (!repos.existsById(id)) {
            throw new RecordNotFoundException("Id not found");
        }
    }
}
