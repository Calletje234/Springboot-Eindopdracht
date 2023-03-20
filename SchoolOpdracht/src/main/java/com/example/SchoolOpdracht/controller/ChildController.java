package com.example.SchoolOpdracht.controller;


import com.example.SchoolOpdracht.dto.ChildDto;
import com.example.SchoolOpdracht.helpers.Util;
import com.example.SchoolOpdracht.service.ChildService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;

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

    @GetMapping("/{id}")
    public ResponseEntity<ChildDto> getChildById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getChildById(id));
    }

    @PostMapping("")
    public ResponseEntity<String> createChild(@Valid @RequestBody ChildDto childDto, BindingResult br) {
        if(br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        Iterable<Long> createdId = service.createChild(childDto);

        URI uri = URI.create(
                ServletUriComponentsBuilder.fromCurrentContextPath().path("/children/" + createdId).toUriString());
        return ResponseEntity.created(uri).body("Child Created");

    }

    @PostMapping("/{id}/{Allergie}")
    public ResponseEntity addAllergie(@PathVariable Long id, @PathVariable String Allergie) {
        return ResponseEntity.ok(service.addAllergies(id, Allergie));
    }

    @PutMapping("/changeFirstname/{id}")
    public ResponseEntity changeFirstName(@Valid @RequestBody ChildDto childDto,
                                          @PathVariable Long id,
                                          BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeFirstName(id, childDto));
    }

    @PutMapping("/changeLastname/{id}")
    public ResponseEntity changeLastName(@Valid @RequestBody ChildDto childDto,
                                         @PathVariable Long id,
                                         BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeLastName(id, childDto));
    }

    @PutMapping("/changeDob/{id}")
    public ResponseEntity changeDateOfBirth(@Valid @RequestBody ChildDto childDto,
                                            @PathVariable Long id,
                                            BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeDateOfBirth(id, childDto));
    }

    @PutMapping("/changeAdress/{id}")
    public ResponseEntity changeAddress (@Valid @RequestBody ChildDto childDto,
                                         @PathVariable Long id,
                                         BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeAddress(id, childDto));
     }

     @PutMapping("/changeCountry/{id}")
     public ResponseEntity changeCountryOfOrigin(@Valid @RequestBody ChildDto childDto,
                                                 @PathVariable Long id,
                                                 BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeCountryOfOrigin(id, childDto));
     }

     @PutMapping("/changeSpoken/{id}")
     public ResponseEntity changeSpokenLanguage(@Valid @RequestBody ChildDto childDto,
                                                @PathVariable Long id,
                                                BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeSpokenLanguage(id, childDto));
    }


    @PutMapping("/changeStartingDate/{id}")
    public ResponseEntity changeStartingDate(@Valid @RequestBody ChildDto childDto,
                                             @PathVariable Long id,
                                             BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeStartingDate(id, childDto));
    }

    @PutMapping("/changeParent/{id}")
    public ResponseEntity changeParent(@Valid @RequestBody ChildDto childDto,
                                       @PathVariable Long id,
                                       BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeParent(id, childDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ChildDto> removeChildById(@PathVariable Long id) {
        return ResponseEntity.ok(service.removeChildById(id));
    }

}
