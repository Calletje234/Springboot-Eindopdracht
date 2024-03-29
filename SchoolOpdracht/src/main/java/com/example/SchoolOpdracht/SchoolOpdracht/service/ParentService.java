package com.example.SchoolOpdracht.SchoolOpdracht.service;


import com.example.SchoolOpdracht.SchoolOpdracht.dto.FileDto;
import com.example.SchoolOpdracht.SchoolOpdracht.exceptions.ParentHasChildrenException;
import com.example.SchoolOpdracht.SchoolOpdracht.exceptions.RecordNotFoundException;
import com.example.SchoolOpdracht.SchoolOpdracht.model.Child;
import com.example.SchoolOpdracht.SchoolOpdracht.repository.ParentRepository;
import com.example.SchoolOpdracht.SchoolOpdracht.dto.ParentDto;
import com.example.SchoolOpdracht.SchoolOpdracht.helpers.Util;
import com.example.SchoolOpdracht.SchoolOpdracht.model.Parent;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParentService {

    private final ParentRepository repos;
    private final FileService fileService;

    // constructor injection
    public ParentService(ParentRepository r, FileService f) {
        this.repos = r;
        this.fileService = f;
    }

    public Long createParent(ParentDto parentDto) {
        Parent newParent = new Parent();

        // map dto to entity
        newParent.setFirstName(parentDto.firstName);
        newParent.setLastName(parentDto.lastName);
        newParent.setPhoneNumber(parentDto.phoneNumber);
        newParent.setAddress(parentDto.address);
        newParent.setCountryOfOrigin(parentDto.countryOfOrigin);
        newParent.setSpokenLanguage(parentDto.spokenLanguage);

        Parent savedParent = repos.save(newParent);
        return savedParent.getParentId();
    }

    public Iterable<ParentDto> getParent(){
        Iterable<Parent> allParents = repos.findAll();
        ArrayList<ParentDto> resultList = new ArrayList<>();
        for(Parent p : allParents) {
            resultList.add(createReturnDto(p));
        }
        return resultList;
    }

    public ParentDto getParentById(Long id) {
        Parent requestedParent = getParentFromRepository(id);
        return createReturnDto(requestedParent);
    }

    public List<FileDto> getFilesByParent(Long id) {
        return fileService.getAssociatedFiled("Parent", id);
    }

    public ParentDto changeFirstName(Long id, ParentDto parentDto) {
        Parent requestedParent = getParentFromRepository(id);
        requestedParent.setFirstName(parentDto.firstName);
        repos.save(requestedParent);
        return createReturnDto(requestedParent);
    }

    public ParentDto changeLastName(Long id, ParentDto parentDto) {
        Parent requestedParent = getParentFromRepository(id);
        requestedParent.setLastName(parentDto.lastName);
        repos.save(requestedParent);
        return createReturnDto(requestedParent);
    }

    public ParentDto changePhoneNumber(Long id, ParentDto parentDto) {
        Parent requestedParent = getParentFromRepository(id);
        requestedParent.setPhoneNumber(parentDto.phoneNumber);
        repos.save(requestedParent);
        return createReturnDto(requestedParent);
    }

    public ParentDto changeAddress(Long id, ParentDto parentDto) {
        Parent requestedParent = getParentFromRepository(id);
        requestedParent.setAddress(parentDto.address);
        repos.save(requestedParent);
        return createReturnDto(requestedParent);
    }

    public ParentDto changeCountryOfOrigin(Long id, ParentDto parentDto) {
        Parent requestedParent = getParentFromRepository(id);
        requestedParent.setCountryOfOrigin(parentDto.countryOfOrigin);
        repos.save(requestedParent);
        return createReturnDto(requestedParent);
    }

    public ParentDto changeSpokenLanguage(Long id, ParentDto parentDto) {
        Parent requestedParent = getParentFromRepository(id);
        requestedParent.setSpokenLanguage(parentDto.spokenLanguage);
        repos.save(requestedParent);
        return createReturnDto(requestedParent);
    }

    public void deleteParentById(Long id) {
        Parent deletedParent = getParentFromRepository(id);
        List<Child> childerensList = deletedParent.getChildren();
        if (childerensList.isEmpty())
            repos.deleteById(deletedParent.getParentId());
        else {
            String childrenIds = childerensList.stream()
                    .map(child -> child.getChildId().toString())
                    .collect(Collectors.joining(", "));
            throw new ParentHasChildrenException("Parent still has Childeren with id's: " + childrenIds + ".\n" +
                    "Please move Childeren to different parent or delete the Children before deleting the parent.");
        }
    }

    public ParentDto createReturnDto(Parent parentModel) {
        ParentDto requestedDto = new ParentDto();
        requestedDto.firstName = parentModel.getFirstName();
        requestedDto.lastName = parentModel.getLastName();
        requestedDto.address = parentModel.getAddress();
        requestedDto.phoneNumber = parentModel.getPhoneNumber();
        requestedDto.countryOfOrigin = parentModel.getCountryOfOrigin();
        requestedDto.spokenLanguage = parentModel.getSpokenLanguage();
        return requestedDto;
    }

    public Parent getParentFromRepository(Long id) {
        return Util.checkAndFindById(id, repos)
                .orElseThrow(() -> new RecordNotFoundException("Parent Record not found by id: " + id));
    }

}
