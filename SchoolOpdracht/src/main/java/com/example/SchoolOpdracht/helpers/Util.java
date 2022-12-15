package com.example.SchoolOpdracht.helpers;

import com.example.SchoolOpdracht.exceptions.RecordNotFoundException;
import org.springframework.data.repository.CrudRepository;


public class Util{
    public static void checkId(Long id, CrudRepository repos) {
        if(id < 0) {
            throw new IndexOutOfBoundsException("Id is not allowed to be negative");
        } else if (!repos.existsById(id)) {
            throw new RecordNotFoundException("Id is not found");
        }
    }
}

//public class Util {
//
//    public static ResponseEntity<> reportErrors(BindingResult br) {
//        StringBuilder sb = new StringBuilder();
//        for (FieldError fe : )
//    }
//}
