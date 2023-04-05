package com.example.SchoolOpdracht.helpers;

import com.example.SchoolOpdracht.exceptions.RecordNotFoundException;
import net.bytebuddy.asm.Advice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.LocalDate;


public class Util{
    public static void checkId(Long id, CrudRepository repos) {
        if(id < 0) {
            throw new IndexOutOfBoundsException("Id is not allowed to be negative");
        } else if (!repos.existsById(id)) {
            throw new RecordNotFoundException("Id not found");
        }
    }

    public static String createErrorMessage(BindingResult br) {
        StringBuilder sb = new StringBuilder();
        for (FieldError fe : br.getFieldErrors()) {
            sb.append(fe.getField() + ": ");
            sb.append(fe.getDefaultMessage());
            sb.append("\n");
        }
        return sb.toString();
    }

    public static LocalDate calculateDueDate(LocalDate birthDateChild) {
        LocalDate startingDate = calculateStartDate(birthDateChild);
        return startingDate.plusWeeks(6);
    }

    public static LocalDate calculateStartDate(LocalDate dateToCalculate) {
        return dateToCalculate.withYear(LocalDate.now().getYear());
    }
}
