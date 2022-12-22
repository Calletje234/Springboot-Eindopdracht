package com.example.SchoolOpdracht.service;

import com.example.SchoolOpdracht.dto.ChildDto;
import com.example.SchoolOpdracht.helpers.Util;
import com.example.SchoolOpdracht.model.Child;
import com.example.SchoolOpdracht.repository.ChildRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ChildService {
    private final ChildRepository repos;

    public ChildService(ChildRepository c) {
        this.repos = c;
    }

    public Long createChild(ChildDto childDto) {
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

        Child savedChild = repos.save(newChild);
        return savedChild.getChildId();
    }

    public Iterable<ChildDto> getAllChildren() {
        Iterable<Child> allChildren = repos.findAll();
        ArrayList<ChildDto> resultList = new ArrayList<>();
        for(Child c: allChildren) {
            ChildDto newChildDto = new ChildDto();
            newChildDto.firstName = c.getFirstName();
            newChildDto.lastName = c.getLastName();
            newChildDto.address = c.getAddress();
            newChildDto.dob = c.getDob();
            newChildDto.startingDate = c.getStartingDate();
            newChildDto.spokenLanguage = c.getSpokenLanguage();
            newChildDto.Allergies = c.getAllergies();
            newChildDto.countryOfOrigin = c.getCountryOfOrigin();
            resultList.add(newChildDto);
        }
        return resultList;
    }

    public ChildDto getChildById(Long id) {
        Util.checkId(id, repos);
        Child requestedChild = repos.findById(id).get();
        ChildDto requestedChildDto = new ChildDto();
        requestedChildDto.firstName = requestedChild.getFirstName();
        requestedChildDto.lastName = requestedChild.getLastName();
        requestedChildDto.address = requestedChild.getAddress();
        requestedChildDto.dob = requestedChild.getDob();
        requestedChildDto.startingDate = requestedChild.getStartingDate();
        requestedChildDto.spokenLanguage = requestedChild.getSpokenLanguage();
        requestedChildDto.Allergies = requestedChild.getAllergies();
        requestedChildDto.countryOfOrigin = requestedChild.getCountryOfOrigin();
        return requestedChildDto;
    }
}
