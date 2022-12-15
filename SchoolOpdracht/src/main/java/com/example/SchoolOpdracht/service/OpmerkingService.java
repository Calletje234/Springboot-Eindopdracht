package com.example.SchoolOpdracht.service;


import com.example.SchoolOpdracht.dto.OpmerkingenDto;
import com.example.SchoolOpdracht.exceptions.RecordNotFoundException;
import com.example.SchoolOpdracht.model.Opmerkingen;
import com.example.SchoolOpdracht.repository.OpmerkingenRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OpmerkingService {

    private final OpmerkingenRepository repos;

    // constructor injection
    public OpmerkingService(OpmerkingenRepository r) {this.repos = r;}

    public Long createOpmerking(OpmerkingenDto opmerkingenDto) {
        Opmerkingen newOpmerking = new Opmerkingen();

        // map dto to entity
        newOpmerking.setTaskId(opmerkingenDto.taskId);
        newOpmerking.setDateOfContact(opmerkingenDto.dateOfContact);
        newOpmerking.setOpmerking(opmerkingenDto.opmerking);

        Opmerkingen savedOpmerking = repos.save(newOpmerking);
        return savedOpmerking.getOpmerkingenId();
    }

    public Iterable<OpmerkingenDto> getAllOpmerkingen() {
        Iterable<Opmerkingen> allOpmerkingen = repos.findAll();
        ArrayList<OpmerkingenDto> resultList = new ArrayList<>();
        for(Opmerkingen o: allOpmerkingen){
            OpmerkingenDto newOpmerkingenDto = new OpmerkingenDto();
            newOpmerkingenDto.taskId = o.getTaskId();
            newOpmerkingenDto.dateOfContact = o.getDateOfContact();
            newOpmerkingenDto.opmerking = o.getOpmerking();
            resultList.add(newOpmerkingenDto);
        }
        return resultList;
    }

    public OpmerkingenDto getOpmerkingById(Long id) {
        if(id < 0) {
            throw new IndexOutOfBoundsException("Id is not allowed to be negative");
        } else if (!repos.existsById(id)) {
            throw new RecordNotFoundException("Id not found");
        } else {
            Opmerkingen requestedOpmerking = repos.findById(id).get();
            OpmerkingenDto requestedOpmerkingDto = new OpmerkingenDto();
            requestedOpmerkingDto.taskId = requestedOpmerking.getTaskId();
            requestedOpmerkingDto.dateOfContact = requestedOpmerking.getDateOfContact();
            requestedOpmerkingDto.opmerking = requestedOpmerking.getOpmerking();
            return requestedOpmerkingDto;
        }
    }
}
