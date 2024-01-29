package com.example.SchoolOpdracht.SchoolOpdracht.service;


import com.example.SchoolOpdracht.SchoolOpdracht.dto.FileDto;
import com.example.SchoolOpdracht.SchoolOpdracht.exceptions.NoFilesFoundException;
import com.example.SchoolOpdracht.SchoolOpdracht.helpers.Util;
import com.example.SchoolOpdracht.SchoolOpdracht.model.File;
import com.example.SchoolOpdracht.SchoolOpdracht.repository.FileRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class FileService {
    private final FileRepository repos;


    public FileService(FileRepository f) {
        this.repos = f;
    }

    public Long createFile(FileDto fileDto) {
        File newFile = new File();

        // map dto to entity
        newFile.setFileName(fileDto.fileName);
        newFile.setFileType(fileDto.fileType);
        newFile.setData(fileDto.fileData);

        File savedFile = repos.save(newFile);
        return savedFile.getId();
    }

    public Iterable<FileDto> getAllFiles() {
        Iterable<File> allFiles = repos.findAll();
        ArrayList<FileDto> resultList = new ArrayList<>();
        for(File f: allFiles) {
            resultList.add(getReturnFileDto(f));
        }
        return resultList;
    }

    public FileDto getFileById(Long id) {
        File requestedFile = getFileModel(id);
        return getReturnFileDto(requestedFile);
    }

    public List<FileDto> getAssociatedFiled(String parentType, Long parentId) {
        List<File> associatedFiles = repos.findByParentIdAndParentType(parentType, parentId);
        if (associatedFiles.isEmpty()) {
            throw new NoFilesFoundException("No Files found with parentType: "
                                            + parentType
                                            + "en parentId: "
                                            + parentId);
        }
        return associatedFiles.stream()
                .map(this::getReturnFileDto)
                .collect(Collectors.toList());
    }

    public FileDto changeFileName(Long id, FileDto fileDto) {
        File fileToChange = getFileModel(id);
        fileToChange.setFileName(fileDto.fileName);
        repos.save(fileToChange);
        return getReturnFileDto(fileToChange);
    }

    public FileDto changeFileType(Long id, FileDto fileDto) {
        File fileToChange = getFileModel(id);
        fileToChange.setFileType(fileDto.fileType);
        repos.save(fileToChange);
        return getReturnFileDto(fileToChange);
    }

    public FileDto changeParentId(Long id, FileDto fileDto) {
        File fileToChange = getFileModel(id);
        fileToChange.setParentId(fileDto.parentId);
        repos.save(fileToChange);
        return getReturnFileDto(fileToChange);

    }
    public FileDto changeParentType(Long id, FileDto fileDto) {
        File fileToChange = getFileModel(id);
        fileToChange.setParentType(fileDto.parentType);
        repos.save(fileToChange);
        return getReturnFileDto(fileToChange);
    }

    public FileDto deleteFileById(Long id) {
        File deletedFile = getFileModel(id);
        repos.deleteById(id);
        return getReturnFileDto(deletedFile);
    }

    public FileDto getReturnFileDto(File fileModel) {
        FileDto dtoToReturn = new FileDto();
        dtoToReturn.fileName = fileModel.getFileName();
        dtoToReturn.fileType = fileModel.getFileType();
        dtoToReturn.fileData = fileModel.getData();
        dtoToReturn.parentType = fileModel.getParentType();
        dtoToReturn.parentId = fileModel.getParentId();
        return dtoToReturn;
    }

    public File getFileModel(Long id) {
        Util.checkId(id, repos);
        return repos.findById(id).get();
    }

}
