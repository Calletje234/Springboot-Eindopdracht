package com.example.SchoolOpdracht.controller;


import com.example.SchoolOpdracht.dto.ChildDto;
import com.example.SchoolOpdracht.helpers.Util;
import com.example.SchoolOpdracht.model.Child;
import com.example.SchoolOpdracht.service.ChildService;
import com.sun.xml.bind.v2.TODO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/childs")
public class ChildController {
    private final ChildService service;
    private Util util;

    public ChildController(ChildService c) {
        this.service = c;
    }

    @GetMapping("")
    public ResponseEntity<Iterable<ChildDto>> getAllChilds() {
        return ResponseEntity.ok(service.getAllChildren());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChildDto> getChildById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getChildById(id));
    }

    @PostMapping("")
    public ResponseEntity<String> createChild(@Valid @RequestBody ChildDto childDto, BindingResult br) {
        if(br.hasErrors()) {
            return new ResponseEntity(util.createErrorMessage(br).toString(), HttpStatus.BAD_REQUEST);
        }
        Long createdId = service.createChild(childDto);

        URI uri = URI.create(
                ServletUriComponentsBuilder.fromCurrentContextPath().path("/children/" + createdId).toUriString());
        return ResponseEntity.created(uri).body("Child Created");

    }



    //TODO create removeChildById function
    @DeleteMapping("/{id}")
    public ResponseEntity<ChildDto> removeChildById(@PathVariable Long id) {
        return ResponseEntity.ok(service.removeChildById(id));
    }

}
