package com.example.SchoolOpdracht.SchoolOpdracht.exceptions;

public class TaskNotRightStatusException extends RuntimeException {
    public TaskNotRightStatusException(String message) {
        super(message);
    }
}
