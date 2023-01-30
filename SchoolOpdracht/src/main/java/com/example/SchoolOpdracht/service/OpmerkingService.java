package com.example.SchoolOpdracht.service;


import com.example.SchoolOpdracht.dto.OpmerkingenDto;
import com.example.SchoolOpdracht.exceptions.RecordNotFoundException;
import com.example.SchoolOpdracht.helpers.Util;
import com.example.SchoolOpdracht.model.Opmerkingen;
import com.example.SchoolOpdracht.repository.OpmerkingenRepository;
import org.springframework.stereotype.Service;

import javax.management.openmbean.OpenMBeanAttributeInfo;
import java.util.ArrayList;

@Service
public class OpmerkingService {

    private final OpmerkingenRepository repos;

    // constructor injection
    public OpmerkingService(OpmerkingenRepository r) {this.repos = r;}

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

    public OpmerkingenDto changeTaskId(Long id, OpmerkingenDto opmerkingenDto) {
        Util.checkId(id, repos);
        Opmerkingen requestOpmerking = repos.findById(id).get();
        requestOpmerking.setTaskId(opmerkingenDto.taskId);
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
        requestDto.taskId = opmerking.getTaskId();
        requestDto.dateOfContact = opmerking.getDateOfContact();
        requestDto.opmerking = opmerking.getOpmerking();
        return requestDto;
    }
}
