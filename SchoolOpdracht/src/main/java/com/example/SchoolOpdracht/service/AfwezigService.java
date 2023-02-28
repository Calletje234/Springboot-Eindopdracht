package com.example.SchoolOpdracht.service;


import com.example.SchoolOpdracht.dto.AfwezigDto;
import com.example.SchoolOpdracht.exceptions.RecordNotFoundException;
import com.example.SchoolOpdracht.helpers.Util;
import com.example.SchoolOpdracht.model.Afwezig;
import com.example.SchoolOpdracht.model.Teacher;
import com.example.SchoolOpdracht.repository.AfwezigRepository;
import com.example.SchoolOpdracht.repository.TeacherRepository;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class AfwezigService {
    private final AfwezigRepository repos;
    private final TeacherRepository teacherRepos;

    //constructor injection
    public AfwezigService(AfwezigRepository r, TeacherRepository tr) {
        this.repos = r;
        this.teacherRepos = tr;
    }

    public Long createAfwezigPeriod(AfwezigDto afwezigDto, Long teacherId) {
        Afwezig newAfwezig = new Afwezig();
        Teacher teacherAfwezig = getTeacherRepos(teacherId);

        // map dto to entity
        newAfwezig.setReason(afwezigDto.reason);
        newAfwezig.setStartDate(afwezigDto.startDate);
        newAfwezig.setEndDate(afwezigDto.endDate);
        newAfwezig.setAfwezigTeacher(teacherAfwezig);

        Afwezig savedAfwezig = repos.save(newAfwezig);
        return savedAfwezig.getAfwezigId();
    }

    public Iterable<AfwezigDto> getAllAfwezig() {
        Iterable<Afwezig> allAfwezig = repos.findAll();
        ArrayList<AfwezigDto> resultList = new ArrayList<>();
        for(Afwezig a: allAfwezig){
            resultList.add(createReturnDto(a));
        }
        return resultList;
    }

    public AfwezigDto getAfwezigById(Long id) {
        Afwezig requestedAfwezig = getAfwezigRepos(id);
        return createReturnDto(requestedAfwezig);
    }

    public AfwezigDto changeReasonAfwezig(Long id, AfwezigDto afwezigDto) {
        Afwezig afwezigToChange = getAfwezigRepos(id);
        afwezigToChange.setReason(afwezigDto.reason);
        repos.save(afwezigToChange);
        return createReturnDto(afwezigToChange);
    }

    public AfwezigDto changeStartingDate(Long id, AfwezigDto afwezigDto) {
        Afwezig afwezigToChange = getAfwezigRepos(id);
        afwezigToChange.setStartDate(afwezigDto.startDate);
        repos.save(afwezigToChange);
        return createReturnDto(afwezigToChange);
    }

    public AfwezigDto changeEndingDate(Long id, AfwezigDto afwezigDto) {
        Afwezig afwezigToChange = getAfwezigRepos(id);
        afwezigToChange.setEndDate(afwezigDto.endDate);
        repos.save(afwezigToChange);
        return createReturnDto(afwezigToChange);
    }

    public AfwezigDto deleteAfwezigById(Long id) {
        Afwezig deletedAfwezig = getAfwezigRepos(id);
        repos.deleteById(id);
        return createReturnDto(deletedAfwezig);
    }

    public AfwezigDto createReturnDto(Afwezig afwezigModel) {
        AfwezigDto requestedDto = new AfwezigDto();
        requestedDto.endDate = afwezigModel.getEndDate();
        requestedDto.teacherId = afwezigModel.getAfwezigTeacher();
        requestedDto.reason = afwezigModel.getReason();
        requestedDto.startDate = afwezigModel.getStartDate();
        return requestedDto;
    }

    public Afwezig getAfwezigRepos(Long id) {
        Util.checkId(id, repos);
        return repos.findById(id).get();
    }

    public Teacher getTeacherRepos(Long id) {
        Util.checkId(id, teacherRepos);
        return teacherRepos.findById(id).get();
    }

}
