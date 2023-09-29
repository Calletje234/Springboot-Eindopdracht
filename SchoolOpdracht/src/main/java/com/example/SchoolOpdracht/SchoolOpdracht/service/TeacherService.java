package com.example.SchoolOpdracht.SchoolOpdracht.service;

import com.example.SchoolOpdracht.SchoolOpdracht.repository.TaskRepository;
import com.example.SchoolOpdracht.SchoolOpdracht.repository.TeacherRepository;
import com.example.SchoolOpdracht.SchoolOpdracht.dto.TaskDto;
import com.example.SchoolOpdracht.SchoolOpdracht.dto.TeacherDto;
import com.example.SchoolOpdracht.SchoolOpdracht.helpers.Util;
import com.example.SchoolOpdracht.SchoolOpdracht.model.Teacher;
import com.example.SchoolOpdracht.SchoolOpdracht.model.Task;
import com.example.SchoolOpdracht.SchoolOpdracht.model.Child;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TeacherService {

    private final TeacherRepository repos;
    private final TaskRepository taskRepos;

    // constructor injection (instead of @Autowired)
    public TeacherService(TeacherRepository r, TaskRepository t) {
        this.repos = r;
        this.taskRepos = t;
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
        Teacher requestedTeacher = getTeacherRepos(id);
        return getReturnTeacherDto(requestedTeacher);
    }

    public TeacherDto changeFirstName(Long id, TeacherDto teacherDto) {
        Teacher teacherToChange = getTeacherRepos(id);
        teacherToChange.setFirstName(teacherDto.firstName);
        repos.save(teacherToChange);
        return getReturnTeacherDto(teacherToChange);
    }

    public int getTaskAmount(Long id) {
        Teacher requestedTeacher = getTeacherRepos(id);
        return requestedTeacher.getTaskAmount();
    }

    public List<TaskDto> getTasksWithStatus(Long id, String status) {
        Teacher requestedTeacher = getTeacherRepos(id);
        List<Task> allTask = requestedTeacher.getTasks();
        List<TaskDto> allTaskDto = new ArrayList<>();
        for(Task task : allTask) {
            if(status.equals("running") && task.getStatus().equals("running")) {
                TaskDto retrievedTask = getTaskDto(task);
                allTaskDto.add(retrievedTask);
            } else if(status.equals("closed") && task.getStatus().equals("closed")) {
                TaskDto retrievedTask = getTaskDto(task);
                allTaskDto.add(retrievedTask);
            } else {
                TaskDto retrieveTask = getTaskDto(task);
                allTaskDto.add(retrieveTask);
            }
        }
        return allTaskDto;
    }

    public TeacherDto changeLastName(Long id, TeacherDto teacherDto) {
        Teacher teacherToChange = getTeacherRepos(id);
        teacherToChange.setLastName(teacherDto.lastName);
        repos.save(teacherToChange);
        return getReturnTeacherDto(teacherToChange);
    }

    public TeacherDto addTaskToTeacher(Long id, Long taskId) {
        Teacher teacherForTaskAdd = getTeacherRepos(id);
        Task taskToAdd = getTaskRepos(taskId);
        List<Task> teacherTaskCopy = teacherForTaskAdd.getTasks();
        int taskAmountCopy = teacherForTaskAdd.getTaskAmount();
        teacherTaskCopy.add(taskToAdd);
        teacherForTaskAdd.setTasks(teacherTaskCopy);
        teacherForTaskAdd.setTaskAmount(taskAmountCopy + 1);
        repos.save(teacherForTaskAdd);
        return getReturnTeacherDto(teacherForTaskAdd);
    }

    public TeacherDto deleteTeacherById(Long id) {
        Teacher deletedTeacher = getTeacherRepos(id);
        repos.deleteById(id);
        return getReturnTeacherDto(deletedTeacher);
    }

    public TeacherDto getReturnTeacherDto(Teacher changedModel) {
        TeacherDto requestDto = new TeacherDto();
        requestDto.firstName = changedModel.getFirstName();
        requestDto.lastName = changedModel.getLastName();
        requestDto.taskAmount = changedModel.getTaskAmount();
        requestDto.tasks = changedModel.getTasks();
        return requestDto;
    }

    public TaskDto getTaskDto(Task taskModel) {
        TaskDto dtoToCreate = new TaskDto();
        Child coupledChild = taskModel.getChild();
        dtoToCreate.childId = coupledChild.getChildId();
        dtoToCreate.dueDate = taskModel.getDueDate();
        dtoToCreate.status = taskModel.getStatus();
        dtoToCreate.teacherId = taskModel.getTaskId();
        return dtoToCreate;
    }

    public Teacher getTeacherRepos(Long id) {
        Util.checkId(id, repos);
        return repos.findById(id).get();
    }

    public Task getTaskRepos(Long id) {
        Util.checkId(id, taskRepos);
        return taskRepos.findById(id).get();
    }
}