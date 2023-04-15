package com.example.SchoolOpdracht.service;

import com.example.SchoolOpdracht.dto.ChildDto;
import com.example.SchoolOpdracht.dto.TaskDto;
import com.example.SchoolOpdracht.helpers.Util;
import com.example.SchoolOpdracht.model.Child;
import com.example.SchoolOpdracht.model.Task;
import com.example.SchoolOpdracht.model.Parent;
import com.example.SchoolOpdracht.repository.ChildRepository;
import com.example.SchoolOpdracht.repository.ParentRepository;
import com.example.SchoolOpdracht.repository.TaskRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Service
public class ChildService {
    private final ChildRepository repos;
    private final ParentRepository parentRepos;
    private final TaskService taskService;

    public ChildService(ChildRepository c, ParentRepository p, TaskService s) {
        this.repos = c;
        this.parentRepos = p;
        this.taskService = s;
    }

    public Iterable<Long> createChild(ChildDto childDto) {
        Child newChild = new Child();

        //map dto to entity
        newChild.setFirstName(childDto.firstName);
        newChild.setLastName(childDto.lastName);
        newChild.setAddress(childDto.address);
        newChild.setDob(childDto.dob);
        newChild.setStartingDate(childDto.startingDate);
        newChild.setSpokenLanguage(childDto.spokenLanguage);
        newChild.setAllergies(childDto.Allergies);
        newChild.setCountryOfOrigin(childDto.countryOfOrigin);
        newChild.setParent(getParentRepos(childDto.parentId));

        Child savedChild = repos.save(newChild);

        Long savedTask = createTask(childDto, savedChild.getChildId());

        ArrayList<Long> idList = new ArrayList<>();
        idList.add(savedChild.getChildId());
        idList.add(savedTask);

        return idList;
    }

    public Iterable<ChildDto> getAllChildren() {
        Iterable<Child> allChildren = repos.findAll();
        ArrayList<ChildDto> resultList = new ArrayList<>();
        for(Child c: allChildren) {
            resultList.add(createReturnDto(c));
        }
        return resultList;
    }

    public ChildDto getChildById(Long id) {
        Child requestedChild = getChildRepos(id);
        return createReturnDto(requestedChild);
    }

    public ChildDto changeFirstName(Long id, ChildDto childDto) {
        Child requestedChild = getChildRepos(id);
        requestedChild.setFirstName(childDto.firstName);
        repos.save(requestedChild);
        return createReturnDto(requestedChild);
    }

    public ChildDto changeLastName(Long id, ChildDto childDto) {
        Child requestedChild = getChildRepos(id);
        requestedChild.setLastName(childDto.lastName);
        repos.save(requestedChild);
        return createReturnDto(requestedChild);
    }

    public ChildDto changeDateOfBirth(Long id, ChildDto childDto) {
        Child requestedChild = getChildRepos(id);
        requestedChild.setDob(childDto.dob);
        repos.save(requestedChild);
        return createReturnDto(requestedChild);
    }

    public ChildDto changeAddress(Long id, ChildDto childDto) {
        Child requestedChild = getChildRepos(id);
        requestedChild.setAddress(childDto.address);
        repos.save(requestedChild);
        return createReturnDto(requestedChild);
    }

    public ChildDto changeCountryOfOrigin(Long id, ChildDto childDto) {
        Child requestedChild = getChildRepos(id);
        requestedChild.setCountryOfOrigin(childDto.countryOfOrigin);
        repos.save(requestedChild);
        return createReturnDto(requestedChild);
    }

    public ChildDto changeSpokenLanguage(Long id, ChildDto childDto) {
        Child requestedChild = getChildRepos(id);
        requestedChild.setSpokenLanguage(childDto.spokenLanguage);
        repos.save(requestedChild);
        return createReturnDto(requestedChild);
    }

    public ChildDto addAllergies(Long id, String addedAllergies) {
        Child requestedChild = getChildRepos(id);
        ArrayList<String> oldAllergies = requestedChild.getAllergies();
        oldAllergies.add(addedAllergies);
        requestedChild.setAllergies(oldAllergies);
        repos.save(requestedChild);
        return createReturnDto(requestedChild);
    }

    public ChildDto changeStartingDate(Long id, ChildDto childDto) {
        Child requestedChild = getChildRepos(id);
        requestedChild.setStartingDate(childDto.startingDate);
        repos.save(requestedChild);
        return createReturnDto(requestedChild);
    }

    public ChildDto changeParent(Long id, ChildDto childDto) {
        Child requestedChild = getChildRepos(id);
        requestedChild.setParent(getParentRepos(childDto.parentId));
        repos.save(requestedChild);
        return createReturnDto(requestedChild);
    }

    public Long createTask(ChildDto childDto, Long childId) {
        TaskDto taskDto = new TaskDto();
        taskDto.childId = childId;
        taskDto.dueDate = (Util.calculateDueDate(childDto.dob));
        taskDto.status = "new";

        return taskService.createTask(taskDto);
    }

    public ChildDto createReturnDto(Child childModel) {
        ChildDto requestDto = new ChildDto();
        requestDto.parentId = childModel.getParentId();
        requestDto.firstName = childModel.getFirstName();
        requestDto.lastName = childModel.getLastName();
        requestDto.address = childModel.getAddress();
        requestDto.dob = childModel.getDob();
        requestDto.startingDate = childModel.getStartingDate();
        requestDto.spokenLanguage = childModel.getSpokenLanguage();
        requestDto.Allergies = childModel.getAllergies();
        requestDto.countryOfOrigin = childModel.getCountryOfOrigin();
        return requestDto;
    }

    public ChildDto removeChildById(Long id) {
        Util.checkId(id, repos);
        Child deletedChild = getChildRepos(id);
        repos.deleteById(id);
        return createReturnDto(deletedChild);
    }

    public Child getChildRepos(Long id) {
        Util.checkId(id, repos);
        return repos.findById(id).get();
    }

    public Parent getParentRepos(Long id) {
        Util.checkId(id, parentRepos);
        return parentRepos.findById(id).get();
    }
}
