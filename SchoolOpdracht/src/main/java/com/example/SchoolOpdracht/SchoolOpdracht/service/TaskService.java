package com.example.SchoolOpdracht.SchoolOpdracht.service;


import com.example.SchoolOpdracht.SchoolOpdracht.Enum.TaskStatus;
import com.example.SchoolOpdracht.SchoolOpdracht.dto.FileDto;
import com.example.SchoolOpdracht.SchoolOpdracht.exceptions.RecordNotFoundException;
import com.example.SchoolOpdracht.SchoolOpdracht.exceptions.TaskNotRightStatusException;
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


import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class TaskService {
    private final TaskRepository repos;
    private final TeacherRepository teacherRepos;
    private final ChildRepository childRepos;
    private final ParentRepository parentRepos;
    private final FileService fileService;

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
        newTask.setChild(getChildFromRepository(taskDto.childId));
        newTask.setAssigned(taskDto.assigned);
        if(taskDto.teacherId != null) {
            newTask.setTeacher(getTeacherFromRepository(taskDto.teacherId));
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
        Task requestedTask = getTaskFromRepository(id);
        return createReturnDto(requestedTask);
    }

    public List<FileDto> getFilesByTask(Long id) {
        return fileService.getAssociatedFiled("Task", id);
    }

    public TaskDto changeTaskStatus(Long taskId, TaskDto taskDto) {
        Task taskToChange = getTaskFromRepository(taskId);
        taskToChange.setStatus(taskDto.status);
        repos.save(taskToChange);
        return createReturnDto(taskToChange);
    }

    public TaskDto changeDueDate(Long taskId, TaskDto taskDto) {
        Task taskToChange = getTaskFromRepository(taskId);
        taskToChange.setDueDate(taskDto.dueDate);
        repos.save(taskToChange);
        return createReturnDto(taskToChange);
    }

    public Long changeAssignedTeacher(Long taskId, TaskDto taskDto) {
        Task taskToChange = getTaskFromRepository(taskId);
        taskToChange.setTeacher(getTeacherFromRepository(taskDto.teacherId));
        repos.save(taskToChange);
        return taskToChange.getTeacher().getTeacherId();
    }

    public TaskDto deleteTaskById(Long taskId) {
        Task deletedTask = getTaskFromRepository(taskId);
        TaskStatus taskStatus = deletedTask.getStatus();
        if ((taskStatus == TaskStatus.CLOSED) || taskStatus == TaskStatus.FINISHED){
            repos.deleteById(taskId);
        } else {
            throw new TaskNotRightStatusException("Task with id: " + taskId + " hasn't right status. Must be 'CLOSED' or 'FINISHED'.");
        }
        repos.deleteById(taskId);
        return createReturnDto(deletedTask);
    }

    public ChildDto getChildInformation(Long taskId) {
        Task requestedTask = getTaskFromRepository(taskId);
        return createChildReturnDto(getChildFromRepository(requestedTask.getChild().getChildId()));
    }

    public ParentDto getParentOfTaskChild(Long taskId) {
        Task requestedTask = getTaskFromRepository(taskId);
        Child coupledChild = getChildFromRepository(requestedTask.getChild().getChildId());
        Parent coupledParent = getParentFromRepository(coupledChild.getParent().getParentId());
        return createParentReturnDto(coupledParent);
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
        Task taskToCheck = getTaskFromRepository(id);
        LocalDate taskDueDate = taskToCheck.getDueDate();
        LocalDate todaysDate = LocalDate.now();
        return ChronoUnit.DAYS.between(taskDueDate, todaysDate);
    }

    public Boolean checkIfTaskIsOverdue(Long id) {
        Date today = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Optional<Task> newTask = repos.findByTaskIdAndDueDateAfter(id, today);
        return newTask.isPresent();
    }

    public Boolean checkIfTeacherIValid(Long teacherId, LocalDate dueDate) {
        Teacher teacherToAssign = getTeacherFromRepository(teacherId);
        List<Afwezig> teacherAfwezigList = teacherToAssign.getAfwezigheid();
        for (Afwezig afwezigList : teacherAfwezigList) {
            if (dueDate.isAfter(afwezigList.getStartDate()) && dueDate.isBefore(afwezigList.getEndDate())) {
                return false;
            }
        }
        return true;
    }

    public void deleteTask(Long id) {
        Task taskToDelete = getTaskFromRepository(id);
        TaskStatus statusOfTask = taskToDelete.getStatus();
        if (statusOfTask != TaskStatus.CLOSED && statusOfTask != TaskStatus.FINISHED) {
            throw new TaskNotRightStatusException("Task isn't closed or finished. Close the task before deleting");
        } else {
            repos.deleteById(taskToDelete.getTaskId());
        }
    }

    public Task getTaskFromRepository(Long id) {
        return Util.checkAndFindById(id, repos)
                .orElseThrow(() -> new RecordNotFoundException("Task Record not found with id: " + id));
    }

    public Teacher getTeacherFromRepository(Long id) {
        return Util.checkAndFindById(id, teacherRepos)
                .orElseThrow(() -> new RecordNotFoundException("Teacher Record not found with id: " + id));
    }

    public Child getChildFromRepository(Long id) {
        return Util.checkAndFindById(id, childRepos)
                .orElseThrow(() -> new RecordNotFoundException("Child Record not found with id: " + id));
    }

    public Parent getParentFromRepository(Long id) {
        return Util.checkAndFindById(id, parentRepos)
                .orElseThrow(() -> new RecordNotFoundException("Parent Record not found with id: " + id));
    }
}
