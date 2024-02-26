package com.example.SchoolOpdracht.SchoolOpdracht.controller;

import com.example.SchoolOpdracht.SchoolOpdracht.exceptions.*;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.IndexOutOfBoundsException;

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

    @ExceptionHandler(TeacherStillHasTaskException.class)
    public ResponseEntity<String> handleTeacherStillHasTaskException(TeacherStillHasTaskException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseEntity<String> handleIndexOutOfBoundsException(IndexOutOfBoundsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(TaskNotRightStatusException.class)
    public ResponseEntity<String> handleTaskNotRightStatusException(TaskNotRightStatusException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(ParentHasChildrenException.class)
    public ResponseEntity<String> handleParentHasChildrenException(ParentHasChildrenException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
