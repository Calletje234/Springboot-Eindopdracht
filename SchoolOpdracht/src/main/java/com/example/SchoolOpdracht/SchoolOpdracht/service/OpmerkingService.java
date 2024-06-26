package com.example.SchoolOpdracht.SchoolOpdracht.service;


import com.example.SchoolOpdracht.SchoolOpdracht.dto.FileDto;
import com.example.SchoolOpdracht.SchoolOpdracht.exceptions.RecordNotFoundException;
import com.example.SchoolOpdracht.SchoolOpdracht.model.Task;
import com.example.SchoolOpdracht.SchoolOpdracht.repository.OpmerkingenRepository;
import com.example.SchoolOpdracht.SchoolOpdracht.dto.OpmerkingenDto;
import com.example.SchoolOpdracht.SchoolOpdracht.helpers.Util;
import com.example.SchoolOpdracht.SchoolOpdracht.model.Opmerking;

import com.example.SchoolOpdracht.SchoolOpdracht.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OpmerkingService {

    private final OpmerkingenRepository repos;
    private final TaskRepository taskRepos;
    private final FileService fileService;

    // constructor injection
    public OpmerkingService(OpmerkingenRepository r, FileService f, TaskRepository tr) {
        this.repos = r;
        this.fileService = f;
        this.taskRepos = tr;
    }

    public Long createOpmerking(OpmerkingenDto opmerkingenDto) {
        Opmerking newOpmerking = new Opmerking();
        Task taskToAddComment = getTaskFromRepository(opmerkingenDto.taskId);

        
        // map dto to entity
        newOpmerking.setTask(taskToAddComment);
        newOpmerking.setDateOfContact(opmerkingenDto.dateOfContact);
        newOpmerking.setOpmerking(opmerkingenDto.opmerking);

        Opmerking savedOpmerking = repos.save(newOpmerking);
        return savedOpmerking.getOpmerkingenId();
    }

    public Iterable<OpmerkingenDto> getAllOpmerkingen() {
        Iterable<Opmerking> allOpmerkingen = repos.findAll();
        ArrayList<OpmerkingenDto> resultList = new ArrayList<>();
        for(Opmerking o: allOpmerkingen){
            resultList.add(createReturnDto(o));
        }
        return resultList;
    }

    public OpmerkingenDto getOpmerkingById(Long id) {
        Opmerking requestedOpmerking = getOpmerkingFromRepository(id);
        return createReturnDto(requestedOpmerking);
    }

    public List<FileDto> getFileByOpmerking(Long id) {
        return fileService.getAssociatedFiled("Opmerking", id);
    }

    public OpmerkingenDto changeOpmerking(Long id, OpmerkingenDto opmerkingenDto) {
        Opmerking requestOpmerking = getOpmerkingFromRepository(id);
        requestOpmerking.setOpmerking(opmerkingenDto.opmerking);
        repos.save(requestOpmerking);
        return createReturnDto(requestOpmerking);
    }

    public OpmerkingenDto changeContactDate(Long id, OpmerkingenDto opmerkingenDto) {
        Opmerking requestOpmerking = getOpmerkingFromRepository(id);
        requestOpmerking.setDateOfContact(opmerkingenDto.dateOfContact);
        repos.save(requestOpmerking);
        return createReturnDto(requestOpmerking);
    }

    public OpmerkingenDto deleteOpmerkingById(Long id) {
        Opmerking deletedOpmerking = getOpmerkingFromRepository(id);
        repos.deleteById(id);
        return createReturnDto(deletedOpmerking);
    }

    public OpmerkingenDto createReturnDto(Opmerking opmerking) {
        OpmerkingenDto requestDto = new OpmerkingenDto();
        requestDto.dateOfContact = opmerking.getDateOfContact();
        requestDto.opmerking = opmerking.getOpmerking();
        return requestDto;
    }

    public Opmerking getOpmerkingFromRepository(Long id) {
        return Util.checkAndFindById(id, repos)
                .orElseThrow(() -> new RecordNotFoundException("Opmerking Record not found with id: " + id));
    }

    public Task getTaskFromRepository(Long id) {
        return Util.checkAndFindById(id, taskRepos)
                .orElseThrow(() -> new RecordNotFoundException("Task Record not found with id: " + id));
    }
}
