package com.example.SchoolOpdracht.SchoolOpdracht.service;


import com.example.SchoolOpdracht.SchoolOpdracht.repository.OpmerkingenRepository;
import com.example.SchoolOpdracht.SchoolOpdracht.repository.TaskRepository;
import com.example.SchoolOpdracht.SchoolOpdracht.dto.OpmerkingenDto;
import com.example.SchoolOpdracht.SchoolOpdracht.helpers.Util;
import com.example.SchoolOpdracht.SchoolOpdracht.model.Opmerkingen;
import com.example.SchoolOpdracht.SchoolOpdracht.model.Task;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OpmerkingService {

    private final OpmerkingenRepository repos;
    private final TaskRepository taskRepos;

    // constructor injection
    public OpmerkingService(OpmerkingenRepository r, TaskRepository t) {
        this.repos = r;
        this.taskRepos = t;
    }

    public Long createOpmerking(OpmerkingenDto opmerkingenDto) {
        Opmerkingen newOpmerking = new Opmerkingen();
        
        // map dto to entity
        newOpmerking.setDateOfContact(opmerkingenDto.dateOfContact);
        newOpmerking.setOpmerking(opmerkingenDto.opmerking);

        Opmerkingen savedOpmerking = repos.save(newOpmerking);
        return savedOpmerking.getOpmerkingenId();
    }

    public Iterable<OpmerkingenDto> getAllOpmerkingen() {
        Iterable<Opmerkingen> allOpmerkingen = repos.findAll();
        ArrayList<OpmerkingenDto> resultList = new ArrayList<>();
        for(Opmerkingen o: allOpmerkingen){
            resultList.add(createReturnDto(o));
        }
        return resultList;
    }

    public OpmerkingenDto getOpmerkingById(Long id) {
        Util.checkId(id, repos);
        Opmerkingen requestedOpmerking = repos.findById(id).get();
        return createReturnDto(requestedOpmerking);
    }

    public OpmerkingenDto changeOpmerking(Long id, OpmerkingenDto opmerkingenDto) {
        Util.checkId(id, repos);
        Opmerkingen requestOpmerking = repos.findById(id).get();
        requestOpmerking.setOpmerking(opmerkingenDto.opmerking);
        repos.save(requestOpmerking);
        return createReturnDto(requestOpmerking);
    }

    public OpmerkingenDto changeContactDate(Long id, OpmerkingenDto opmerkingenDto) {
        Util.checkId(id, repos);
        Opmerkingen requestOpmerking = repos.findById(id).get();
        requestOpmerking.setDateOfContact(opmerkingenDto.dateOfContact);
        repos.save(requestOpmerking);
        return createReturnDto(requestOpmerking);
    }

    public OpmerkingenDto deleteOpmerkingById(Long id) {
        Util.checkId(id, repos);
        Opmerkingen deletedOpmerking = repos.findById(id).get();
        repos.deleteById(id);
        return createReturnDto(deletedOpmerking);
    }

    public OpmerkingenDto createReturnDto(Opmerkingen opmerking) {
        OpmerkingenDto requestDto = new OpmerkingenDto();
        requestDto.dateOfContact = opmerking.getDateOfContact();
        requestDto.opmerking = opmerking.getOpmerking();
        return requestDto;
    }

    public Task getTaskRepos(Long id) {
        Util.checkId(id, taskRepos);
        return taskRepos.findById(id).get();
    }

    public Opmerkingen getOpmerkingRepos(Long id) {
        Util.checkId(id, repos);
        return repos.findById(id).get();
    }
}
