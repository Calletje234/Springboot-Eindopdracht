package com.example.SchoolOpdracht.SchoolOpdracht.helpers;

import org.springframework.data.repository.CrudRepository;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.LocalDate;
import java.util.Optional;


public class Util{
    public static <T> Optional<T> checkAndFindById(Long id, CrudRepository<T, Long> repos) {
        if(id < 0) {
            throw new IndexOutOfBoundsException("Id is not allowed to be negative");
        }
        return repos.findById(id);
    }

    public static String createErrorMessage(BindingResult br) {
        StringBuilder sb = new StringBuilder();
        for (FieldError fe : br.getFieldErrors()) {
            sb.append(fe.getField());
            sb.append(": ");
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
