package com.example.SchoolOpdracht.SchoolOpdracht.service;


import com.example.SchoolOpdracht.SchoolOpdracht.dto.FileDto;
import com.example.SchoolOpdracht.SchoolOpdracht.exceptions.RecordNotFoundException;
import com.example.SchoolOpdracht.SchoolOpdracht.repository.OpmerkingenRepository;
import com.example.SchoolOpdracht.SchoolOpdracht.dto.OpmerkingenDto;
import com.example.SchoolOpdracht.SchoolOpdracht.helpers.Util;
import com.example.SchoolOpdracht.SchoolOpdracht.model.Opmerkingen;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OpmerkingService {

    private final OpmerkingenRepository repos;
    private final FileService fileService;

    // constructor injection
    public OpmerkingService(OpmerkingenRepository r, FileService f) {
        this.repos = r;
        this.fileService = f;
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
        Opmerkingen requestedOpmerking = getOpmerkingFromRepository(id);
        return createReturnDto(requestedOpmerking);
    }

    public List<FileDto> getFileByOpmerking(Long id) {
        return fileService.getAssociatedFiled("Opmerking", id);
    }

    public OpmerkingenDto changeOpmerking(Long id, OpmerkingenDto opmerkingenDto) {
        Opmerkingen requestOpmerking = getOpmerkingFromRepository(id);
        requestOpmerking.setOpmerking(opmerkingenDto.opmerking);
        repos.save(requestOpmerking);
        return createReturnDto(requestOpmerking);
    }

    public OpmerkingenDto changeContactDate(Long id, OpmerkingenDto opmerkingenDto) {
        Opmerkingen requestOpmerking = getOpmerkingFromRepository(id);
        requestOpmerking.setDateOfContact(opmerkingenDto.dateOfContact);
        repos.save(requestOpmerking);
        return createReturnDto(requestOpmerking);
    }

    public OpmerkingenDto deleteOpmerkingById(Long id) {
        Opmerkingen deletedOpmerking = getOpmerkingFromRepository(id);
        repos.deleteById(id);
        return createReturnDto(deletedOpmerking);
    }

    public OpmerkingenDto createReturnDto(Opmerkingen opmerking) {
        OpmerkingenDto requestDto = new OpmerkingenDto();
        requestDto.dateOfContact = opmerking.getDateOfContact();
        requestDto.opmerking = opmerking.getOpmerking();
        return requestDto;
    }

    public Opmerkingen getOpmerkingFromRepository(Long id) {
        return Util.checkAndFindById(id, repos)
                .orElseThrow(() -> new RecordNotFoundException("Opmerking Record not found with id: " + id));
    }
}
