package com.example.SchoolOpdracht.SchoolOpdracht.service;


import com.example.SchoolOpdracht.SchoolOpdracht.dto.FileDto;
import com.example.SchoolOpdracht.SchoolOpdracht.exceptions.RecordNotFoundException;
import com.example.SchoolOpdracht.SchoolOpdracht.repository.AfwezigRepository;
import com.example.SchoolOpdracht.SchoolOpdracht.repository.TeacherRepository;
import com.example.SchoolOpdracht.SchoolOpdracht.dto.AfwezigDto;
import com.example.SchoolOpdracht.SchoolOpdracht.helpers.Util;
import com.example.SchoolOpdracht.SchoolOpdracht.model.Afwezig;
import com.example.SchoolOpdracht.SchoolOpdracht.model.Teacher;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AfwezigService {
    private final AfwezigRepository repos;
    private final TeacherRepository teacherRepos;
    private final FileService fileService;

    //constructor injection
    public AfwezigService(AfwezigRepository r, TeacherRepository tr, FileService fs) {
        this.repos = r;
        this.teacherRepos = tr;
        this.fileService = fs;
    }

    public Long createAfwezigPeriod(AfwezigDto afwezigDto) {
        Afwezig newAfwezig = new Afwezig();

        // map dto to entity
        newAfwezig.setReason(afwezigDto.reason);
        newAfwezig.setStartDate(afwezigDto.startDate);
        newAfwezig.setEndDate(afwezigDto.endDate);
        newAfwezig.setAfwezigTeacher(getTeacherFromRepository(afwezigDto.teacherId));

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

    public List<FileDto> getFileByAfwezigMelding(Long id) {
        return fileService.getAssociatedFiled("Afwezig", id);
    }

    public AfwezigDto getAfwezigById(Long id) {
        Afwezig requestedAfwezig = getAfwezigFromRepository(id);
        return createReturnDto(requestedAfwezig);
    }

    public AfwezigDto changeReasonAfwezig(Long id, AfwezigDto afwezigDto) {
        Afwezig afwezigToChange = getAfwezigFromRepository(id);
        afwezigToChange.setReason(afwezigDto.reason);
        repos.save(afwezigToChange);
        return createReturnDto(afwezigToChange);
    }

    public AfwezigDto changeStartingDate(Long id, AfwezigDto afwezigDto) {
        Afwezig afwezigToChange = getAfwezigFromRepository(id);
        afwezigToChange.setStartDate(afwezigDto.startDate);
        repos.save(afwezigToChange);
        return createReturnDto(afwezigToChange);
    }

    public AfwezigDto changeEndingDate(Long id, AfwezigDto afwezigDto) {
        Afwezig afwezigToChange = getAfwezigFromRepository(id);
        afwezigToChange.setEndDate(afwezigDto.endDate);
        repos.save(afwezigToChange);
        return createReturnDto(afwezigToChange);
    }

    public AfwezigDto deleteAfwezigById(Long id) {
        Afwezig deletedAfwezig = getAfwezigFromRepository(id);
        repos.deleteById(id);
        return createReturnDto(deletedAfwezig);
    }

    public AfwezigDto createReturnDto(Afwezig afwezigModel) {
        AfwezigDto requestedDto = new AfwezigDto();
        requestedDto.endDate = afwezigModel.getEndDate();
        requestedDto.teacherId = afwezigModel.getAfwezigTeacher().getTeacherId();
        requestedDto.reason = afwezigModel.getReason();
        requestedDto.startDate = afwezigModel.getStartDate();
        return requestedDto;
    }

    public Afwezig getAfwezigFromRepository(Long id) {
        return Util.checkAndFindById(id, repos)
                .orElseThrow(() -> new RecordNotFoundException("Afwezig Record not found with ID: " + id));
    }

    public Teacher getTeacherFromRepository(Long id) {
        return Util.checkAndFindById(id, teacherRepos)
                .orElseThrow(() -> new RecordNotFoundException("Teacher Record not found with ID: " + id));
    }

}
