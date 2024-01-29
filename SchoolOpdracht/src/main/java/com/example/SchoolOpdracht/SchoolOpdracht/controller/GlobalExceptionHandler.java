package com.example.SchoolOpdracht.SchoolOpdracht.controller;

import com.example.SchoolOpdracht.SchoolOpdracht.exceptions.NoFilesFoundException;
import com.example.SchoolOpdracht.SchoolOpdracht.exceptions.NoRoleFoundException;
import com.example.SchoolOpdracht.SchoolOpdracht.exceptions.RecordNotFoundException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoFilesFoundException.class)
    public ResponseEntity<String> handleNoFilesFoundException(NoFilesFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(NoRoleFoundException.class)
    public  ResponseEntity<String> handleNoRoleFoundException(NoRoleFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<String> handleRecordNotFoundException(RecordNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}