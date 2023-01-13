package com.example.SchoolOpdracht.service;


import com.example.SchoolOpdracht.dto.ParentDto;
import com.example.SchoolOpdracht.exceptions.RecordNotFoundException;
import com.example.SchoolOpdracht.helpers.Util;
import com.example.SchoolOpdracht.model.Parent;
import com.example.SchoolOpdracht.repository.ParentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ParentService {

    private final ParentRepository repos;

    // constructor injection
    public ParentService(ParentRepository r) {this.repos = r;}

    public Long createParent(ParentDto parentDto) {
        Parent newParent = new Parent();

        // map dto to entity
        newParent.setFirstName(parentDto.firstName);
        newParent.setLastName(parentDto.lastName);
        newParent.setPhoneNumber(parentDto.phoneNumber);
        newParent.setAddress(parentDto.address);
        newParent.setCountryOfOrigin(parentDto.countryOfOrigin);
        newParent.setSpokenLanguage(parentDto.spokenLanguage);
        newParent.setChildId(parentDto.childId);

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
        Parent requestedParent = getParentRepos(id);
        return createReturnDto(requestedParent);
    }

    public ParentDto changeChildId(Long id, ParentDto parentDto) {
        Parent requestedParent = getParentRepos(id);
        requestedParent.setChildId(parentDto.childId);
        repos.save(requestedParent);
        return createReturnDto(requestedParent);
    }

    public ParentDto changeFirstName(Long id, ParentDto parentDto) {
        Parent requestedParent = getParentRepos(id);
        requestedParent.setFirstName(parentDto.firstName);
        repos.save(requestedParent);
        return createReturnDto(requestedParent);
    }

    public ParentDto changeLastName(Long id, ParentDto parentDto) {
        Parent requestedParent = getParentRepos(id);
        requestedParent.setLastName(parentDto.lastName);
        repos.save(requestedParent);
        return createReturnDto(requestedParent);
    }

    public ParentDto changePhoneNumber(Long id, ParentDto parentDto) {
        Parent requestedParent = getParentRepos(id);
        requestedParent.setPhoneNumber(parentDto.phoneNumber);
        repos.save(requestedParent);
        return createReturnDto(requestedParent);
    }

    public ParentDto changeAddress(Long id, ParentDto parentDto) {
        Parent requestedParent = getParentRepos(id);
        requestedParent.setAddress(parentDto.address);
        repos.save(requestedParent);
        return createReturnDto(requestedParent);
    }

    public ParentDto changeCountryOfOrigin(Long id, ParentDto parentDto) {
        Parent requestedParent = getParentRepos(id);
        requestedParent.setCountryOfOrigin(parentDto.countryOfOrigin);
        repos.save(requestedParent);
        return createReturnDto(requestedParent);
    }

    public ParentDto changeSpokenLanguage(Long id, ParentDto parentDto) {
        Parent requestedParent = getParentRepos(id);
        requestedParent.setSpokenLanguage(parentDto.spokenLanguage);
        repos.save(requestedParent);
        return createReturnDto(requestedParent);
    }

    public ParentDto createReturnDto(Parent parentModel) {
        ParentDto requestedDto = new ParentDto();
        requestedDto.firstName = parentModel.getFirstName();
        requestedDto.lastName = parentModel.getLastName();
        requestedDto.address = parentModel.getAddress();
        requestedDto.phoneNumber = parentModel.getPhoneNumber();
        requestedDto.countryOfOrigin = parentModel.getCountryOfOrigin();
        requestedDto.spokenLanguage = parentModel.getSpokenLanguage();
        requestedDto.childId = parentModel.getChildId();
        return requestedDto;
    }

    public Parent getParentRepos(Long id) {
        Util.checkId(id, repos);
        return repos.findById(id).get();
    }

}
