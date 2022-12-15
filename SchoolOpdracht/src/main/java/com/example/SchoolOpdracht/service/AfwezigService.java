package com.example.SchoolOpdracht.service;


import com.example.SchoolOpdracht.dto.AfwezigDto;
import com.example.SchoolOpdracht.exceptions.RecordNotFoundException;
import com.example.SchoolOpdracht.model.Afwezig;
import com.example.SchoolOpdracht.repository.AfwezigRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AfwezigService {
    private final AfwezigRepository repos;

    //constructor injection
    public AfwezigService(AfwezigRepository r) {
        this.repos = r;
    }

    public Long createAfwezigPeriod(AfwezigDto afwezigDto) {
        Afwezig newAfwezig = new Afwezig();

        // map dto to entity
        newAfwezig.setTeacherId(afwezigDto.teacherId);
        newAfwezig.setReason(afwezigDto.reason);
        newAfwezig.setStartDate(afwezigDto.startDate);
        newAfwezig.setEndDate(afwezigDto.endDate);

        Afwezig savedAfwezig = repos.save(newAfwezig);
        return savedAfwezig.getAfwezigId();
    }

    public Iterable<AfwezigDto> getAllAfwezig() {
        Iterable<Afwezig> allAfwezig = repos.findAll();
        ArrayList<AfwezigDto> resultList = new ArrayList<>();
        for(Afwezig a: allAfwezig){
            AfwezigDto newAfwezig = new AfwezigDto();
            newAfwezig.teacherId = a.getTeacherId();
            newAfwezig.reason = a.getReason();
            newAfwezig.startDate = a.getStartDate();
            newAfwezig.endDate = a.getEndDate();
            resultList.add(newAfwezig);
        }
        return resultList;
    }

    public AfwezigDto getAfwezigeById(Long id) {
        if(id < 0) {
            throw new IndexOutOfBoundsException("Id is not allowed to be negative");
        } else if (!repos.existsById(id)) {
            throw new RecordNotFoundException("Id not found");
        } else {
            Afwezig requestedAfwezige = repos.findById(id).get();
            AfwezigDto requestedAfwezigeDto = new AfwezigDto();
            requestedAfwezigeDto.teacherId = requestedAfwezige.getTeacherId();
            requestedAfwezigeDto.reason = requestedAfwezige.getReason();
            requestedAfwezigeDto.startDate = requestedAfwezige.getStartDate();
            requestedAfwezigeDto.endDate = requestedAfwezige.getEndDate();
            return requestedAfwezigeDto;
        }
    }
}
