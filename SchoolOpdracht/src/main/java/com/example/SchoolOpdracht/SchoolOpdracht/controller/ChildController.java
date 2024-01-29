package com.example.SchoolOpdracht.SchoolOpdracht.controller;


import com.example.SchoolOpdracht.SchoolOpdracht.dto.ChildDto;
import com.example.SchoolOpdracht.SchoolOpdracht.dto.FileDto;
import com.example.SchoolOpdracht.SchoolOpdracht.helpers.Util;
import com.example.SchoolOpdracht.SchoolOpdracht.service.ChildService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

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

    @GetMapping("/{id}/files")
    public ResponseEntity<List<FileDto>> getAssociatedFiles(@PathVariable Long id) {
        return ResponseEntity.ok(service.getFileByChild(id));
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

    @PutMapping("/{id}/firstname")
    public ResponseEntity changeFirstName(@Valid @RequestBody ChildDto childDto,
                                          @PathVariable Long id,
                                          BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeFirstName(id, childDto));
    }

    @PutMapping("/{id}/lastname")
    public ResponseEntity changeLastName(@Valid @RequestBody ChildDto childDto,
                                         @PathVariable Long id,
                                         BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeLastName(id, childDto));
    }

    @PutMapping("/{id}/dob")
    public ResponseEntity changeDateOfBirth(@Valid @RequestBody ChildDto childDto,
                                            @PathVariable Long id,
                                            BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeDateOfBirth(id, childDto));
    }

    @PutMapping("/{id}/adress")
    public ResponseEntity changeAddress (@Valid @RequestBody ChildDto childDto,
                                         @PathVariable Long id,
                                         BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeAddress(id, childDto));
     }

     @PutMapping("/{id}/country")
     public ResponseEntity changeCountryOfOrigin(@Valid @RequestBody ChildDto childDto,
                                                 @PathVariable Long id,
                                                 BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeCountryOfOrigin(id, childDto));
     }

     @PutMapping("/{id}/spokenLanguage")
     public ResponseEntity changeSpokenLanguage(@Valid @RequestBody ChildDto childDto,
                                                @PathVariable Long id,
                                                BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeSpokenLanguage(id, childDto));
    }


    @PutMapping("/{id}/startingDate")
    public ResponseEntity changeStartingDate(@Valid @RequestBody ChildDto childDto,
                                             @PathVariable Long id,
                                             BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeStartingDate(id, childDto));
    }

    @PutMapping("/{id}/parent")
    public ResponseEntity changeParent(@Valid @RequestBody ChildDto childDto,
                                       @PathVariable Long id,
                                       BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeParent(id, childDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeChildById(@PathVariable Long id) {
        service.removeChildById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
