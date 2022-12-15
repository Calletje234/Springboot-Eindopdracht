package com.example.SchoolOpdracht.service;


import com.example.SchoolOpdracht.dto.ParentDto;
import com.example.SchoolOpdracht.exceptions.RecordNotFoundException;
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
            ParentDto newParentDto = new ParentDto();
            newParentDto.firstName = p.getFirstName();
            newParentDto.lastName = p.getLastName();
            newParentDto.phoneNumber = p.getPhoneNumber();
            newParentDto.address = p.getAddress();
            newParentDto.countryOfOrigin = p.getCountryOfOrigin();
            newParentDto.spokenLanguage = p.getSpokenLanguage();
            newParentDto.childId = p.getChildId();
            resultList.add(newParentDto);
        }
        return resultList;
    }

    public ParentDto getParentById(Long id) {
        if(id < 0) {
            throw new IndexOutOfBoundsException("Id is not allowed to be negative");
        } else if(!repos.existsById(id)) {
            throw new RecordNotFoundException("Id not found");
        } else {
            Parent requestedParent = repos.findById(id).get();
            ParentDto requestedParentDto = new ParentDto();
            requestedParentDto.firstName = requestedParent.getFirstName();
            requestedParentDto.lastName = requestedParent.getLastName();
            requestedParentDto.address = requestedParent.getAddress();
            requestedParentDto.phoneNumber = requestedParent.getPhoneNumber();
            requestedParentDto.countryOfOrigin = requestedParent.getCountryOfOrigin();
            requestedParentDto.spokenLanguage = requestedParent.getSpokenLanguage();
            requestedParentDto.childId = requestedParent.getChildId();
            return requestedParentDto;
        }
    }

}
