package com.example.SchoolOpdracht.controller;


import com.example.SchoolOpdracht.dto.ChildDto;
import com.example.SchoolOpdracht.model.Child;
import com.example.SchoolOpdracht.service.ChildService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/childs")
public class ChildController {
    private final ChildService service;

    public ChildController(ChildService c) {
        this.service = c;
    }

    @GetMapping("")
    public ResponseEntity<Iterable<ChildDto>> getAllChilds() {
        return ResponseEntity.ok(service.getAllChildren());
    }


}
