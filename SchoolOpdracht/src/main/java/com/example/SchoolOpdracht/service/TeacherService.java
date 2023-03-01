package com.example.SchoolOpdracht.service;

import com.example.SchoolOpdracht.dto.TeacherDto;
// import com.example.SchoolOpdracht.exceptions.RecordNotFoundException;
import com.example.SchoolOpdracht.helpers.Util;
import com.example.SchoolOpdracht.model.Teacher;
import com.example.SchoolOpdracht.model.Task;
import com.example.SchoolOpdracht.repository.TaskRepository;
import com.example.SchoolOpdracht.repository.TeacherRepository;
import org.springframework.stereotype.Service;

// import javax.validation.constraints.Null;
import java.util.ArrayList;

// import static java.util.Objects.isNull;

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

    public ArrayList<Task> getAllOpenTasks(Long id) {
        Teacher requestedTeacher = getTeacherRepos(id);
        ArrayList<Task> allTasks = requestedTeacher.getTasks();
        ArrayList<Task> runningTasks = new ArrayList<>();
        for (Task task : allTasks) {
            if(task.getStatus() != "Running") {
                runningTasks.add(task);
            }
        }
        return runningTasks;
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
        ArrayList<Task> teacherTaskCopy = teacherForTaskAdd.getTasks();
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
        return requestDto;
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