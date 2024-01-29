package com.example.SchoolOpdracht.SchoolOpdracht.service;

import com.example.SchoolOpdracht.SchoolOpdracht.dto.FileDto;
import com.example.SchoolOpdracht.SchoolOpdracht.exceptions.RecordNotFoundException;
import com.example.SchoolOpdracht.SchoolOpdracht.repository.ChildRepository;
import com.example.SchoolOpdracht.SchoolOpdracht.repository.ParentRepository;
import com.example.SchoolOpdracht.SchoolOpdracht.dto.ChildDto;
import com.example.SchoolOpdracht.SchoolOpdracht.dto.TaskDto;
import com.example.SchoolOpdracht.SchoolOpdracht.helpers.Util;
import com.example.SchoolOpdracht.SchoolOpdracht.model.Child;
import com.example.SchoolOpdracht.SchoolOpdracht.model.Parent;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChildService {
    private final ChildRepository repos;
    private final ParentRepository parentRepos;
    private final TaskService taskService;
    private final FileService fileService;

    public ChildService(ChildRepository c, ParentRepository p, TaskService s, FileService f) {
        this.repos = c;
        this.parentRepos = p;
        this.taskService = s;
        this.fileService = f;
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
        newChild.setParent(getParentFromRepository(childDto.parentId));

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
        Child requestedChild = getChildFromRepository(id);
        return createReturnDto(requestedChild);
    }

    public List<FileDto> getFileByChild(Long id) {
        return fileService.getAssociatedFiled("Child", id);
    }

    public ChildDto changeFirstName(Long id, ChildDto childDto) {
        Child requestedChild = getChildFromRepository(id);
        requestedChild.setFirstName(childDto.firstName);
        repos.save(requestedChild);
        return createReturnDto(requestedChild);
    }

    public ChildDto changeLastName(Long id, ChildDto childDto) {
        Child requestedChild = getChildFromRepository(id);
        requestedChild.setLastName(childDto.lastName);
        repos.save(requestedChild);
        return createReturnDto(requestedChild);
    }

    public ChildDto changeDateOfBirth(Long id, ChildDto childDto) {
        Child requestedChild = getChildFromRepository(id);
        requestedChild.setDob(childDto.dob);
        repos.save(requestedChild);
        return createReturnDto(requestedChild);
    }

    public ChildDto changeAddress(Long id, ChildDto childDto) {
        Child requestedChild = getChildFromRepository(id);
        requestedChild.setAddress(childDto.address);
        repos.save(requestedChild);
        return createReturnDto(requestedChild);
    }

    public ChildDto changeCountryOfOrigin(Long id, ChildDto childDto) {
        Child requestedChild = getChildFromRepository(id);
        requestedChild.setCountryOfOrigin(childDto.countryOfOrigin);
        repos.save(requestedChild);
        return createReturnDto(requestedChild);
    }

    public ChildDto changeSpokenLanguage(Long id, ChildDto childDto) {
        Child requestedChild = getChildFromRepository(id);
        requestedChild.setSpokenLanguage(childDto.spokenLanguage);
        repos.save(requestedChild);
        return createReturnDto(requestedChild);
    }

    public ChildDto addAllergies(Long id, String addedAllergies) {
        Child requestedChild = getChildFromRepository(id);
        ArrayList<String> oldAllergies = requestedChild.getAllergies();
        oldAllergies.add(addedAllergies);
        requestedChild.setAllergies(oldAllergies);
        repos.save(requestedChild);
        return createReturnDto(requestedChild);
    }

    public ChildDto changeStartingDate(Long id, ChildDto childDto) {
        Child requestedChild = getChildFromRepository(id);
        requestedChild.setStartingDate(childDto.startingDate);
        repos.save(requestedChild);
        return createReturnDto(requestedChild);
    }

    public ChildDto changeParent(Long id, ChildDto childDto) {
        Child requestedChild = getChildFromRepository(id);
        requestedChild.setParent(getParentFromRepository(childDto.parentId));
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
        requestDto.parentId = childModel.getParent().getParentId();
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
        Child deletedChild = getChildFromRepository(id);
        repos.deleteById(id);
        return createReturnDto(deletedChild);
    }

    public Child getChildFromRepository(Long id) {
        return Util.checkAndFindById(id, repos)
                .orElseThrow(() -> new RecordNotFoundException("Child Record not found with id: " + id));

    }

    public Parent getParentFromRepository(Long id) {
        return Util.checkAndFindById(id, parentRepos)
                .orElseThrow(() -> new RecordNotFoundException("Parent Record not found with id: " + id));
    }
}
