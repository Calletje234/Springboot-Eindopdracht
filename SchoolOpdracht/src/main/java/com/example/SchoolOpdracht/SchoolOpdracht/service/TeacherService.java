package com.example.SchoolOpdracht.SchoolOpdracht.service;

import com.example.SchoolOpdracht.SchoolOpdracht.dto.FileDto;
import com.example.SchoolOpdracht.SchoolOpdracht.exceptions.RecordNotFoundException;
import com.example.SchoolOpdracht.SchoolOpdracht.exceptions.TeacherStillHasTaskException;
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
import java.util.Objects;


@Service
public class TeacherService {

    private final TeacherRepository repos;
    private final TaskRepository taskRepos;
    private final FileService fileService;

    // constructor injection (instead of @Autowired)
    public TeacherService(TeacherRepository r, TaskRepository t, FileService f) {
        this.repos = r;
        this.taskRepos = t;
        this.fileService = f;
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
        Teacher requestedTeacher = getTeacherFromRepository(id);
        return getReturnTeacherDto(requestedTeacher);
    }

    public List<FileDto> getFileByTeacher(Long id) {
        return fileService.getAssociatedFiled("Teacher", id);
    }

    public TeacherDto changeFirstName(Long id, TeacherDto teacherDto) {
        Teacher teacherToChange = getTeacherFromRepository(id);
        teacherToChange.setFirstName(teacherDto.firstName);
        repos.save(teacherToChange);
        return getReturnTeacherDto(teacherToChange);
    }

    public int getTaskAmount(Long id) {
        Teacher requestedTeacher = getTeacherFromRepository(id);
        return requestedTeacher.getTaskAmount();
    }

    public List<TaskDto> getTasksWithStatus(Long id, String status) {
        Teacher requestedTeacher = getTeacherFromRepository(id);
        List<Task> allTask = requestedTeacher.getTasks();
        List<TaskDto> allTaskDto = new ArrayList<>();
        for(Task task : allTask) {
            if(task.getStatus().equals(status)) {
                TaskDto retrievedTask = getTaskDto(task);
                allTaskDto.add(retrievedTask);
            } else if (Objects.equals(status, "")) {
                TaskDto retrievedTask = getTaskDto(task);
                allTaskDto.add(retrievedTask);
            }
        }
        return allTaskDto;
    }

    public TeacherDto changeLastName(Long id, TeacherDto teacherDto) {
        Teacher teacherToChange = getTeacherFromRepository(id);
        teacherToChange.setLastName(teacherDto.lastName);
        repos.save(teacherToChange);
        return getReturnTeacherDto(teacherToChange);
    }

    public TeacherDto addTaskToTeacher(Long id, Long taskId) {
        Teacher teacherForTaskAdd = getTeacherFromRepository(id);
        Task taskToAdd = getTaskFromRepository(taskId);
        List<Task> teacherTaskCopy = teacherForTaskAdd.getTasks();
        int taskAmountCopy = teacherForTaskAdd.getTaskAmount();
        teacherTaskCopy.add(taskToAdd);
        teacherForTaskAdd.setTasks(teacherTaskCopy);
        teacherForTaskAdd.setTaskAmount(taskAmountCopy + 1);
        repos.save(teacherForTaskAdd);
        return getReturnTeacherDto(teacherForTaskAdd);
    }

    public TeacherDto deleteTeacherById(Long id) {
        Teacher deletedTeacher = getTeacherFromRepository(id);
        List<Task> tasks = deletedTeacher.getTasks();
        if (!tasks.isEmpty()) {
            throw new TeacherStillHasTaskException("Teacher still has tasks assigned");
        }
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

    public Teacher getTeacherFromRepository(Long id) {
        return Util.checkAndFindById(id, repos)
                .orElseThrow(() -> new RecordNotFoundException("Teacher Record not found with id: " + id));
    }

    public Task getTaskFromRepository(Long id) {
        return Util.checkAndFindById(id, taskRepos)
                .orElseThrow(() -> new RecordNotFoundException("Task Record not found with id: " + id));
    }
}