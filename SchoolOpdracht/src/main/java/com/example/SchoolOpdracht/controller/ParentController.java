package com.example.SchoolOpdracht.controller;


import com.example.SchoolOpdracht.dto.ParentDto;
import com.example.SchoolOpdracht.helpers.Util;
import com.example.SchoolOpdracht.model.Parent;
import com.example.SchoolOpdracht.service.ParentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.naming.Binding;
import javax.validation.Valid;
import java.net.URI;
import java.net.http.HttpResponse;


@RestController
@RequestMapping("/parents")
public class ParentController {
    private final ParentService service;

    public ParentController(ParentService p) {
        this.service = p;
    }

    @GetMapping("")
    public ResponseEntity<Iterable<ParentDto>> getAllParents() {
        return ResponseEntity.ok(service.getParent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParentDto> getParentById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getParentById(id));
    }

    @PostMapping("")
    public ResponseEntity<String> createParent(@Valid @RequestBody ParentDto parentDto, BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity<>(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        Long createdId = service.createParent(parentDto);

        URI uri = URI.create(
                ServletUriComponentsBuilder.fromCurrentContextPath().path("/parents/" + createdId).toUriString());
        return ResponseEntity.created(uri).body("Parent Created");
    }

    @PutMapping("/changeFirstName/{id}")
    public ResponseEntity changeFirstName(@Valid @RequestBody ParentDto parentDto,
                                          @PathVariable Long id,
                                          BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeFirstName(id, parentDto));
    }

    @PutMapping("/changeLastName/{id}")
    public ResponseEntity changeLastName(@Valid @RequestBody ParentDto parentDto,
                                         @PathVariable Long id,
                                         BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeLastName(id, parentDto));
    }

    @PutMapping("/changePhoneNumber/{id}")
    public ResponseEntity changePhoneNumber(@Valid @RequestBody ParentDto parentDto,
                                            @PathVariable Long id,
                                            BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changePhoneNumber(id, parentDto));
    }

    @PutMapping("/changeAddress/{id}")
    public ResponseEntity changeAddress(@Valid @RequestBody ParentDto parentDto,
                                        @PathVariable Long id,
                                        BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeAddress(id, parentDto));
    }

    @PutMapping("/changeCountryOfOrigin/{id}")
    public ResponseEntity changeCountryOfOrigin(@Valid @RequestBody ParentDto parentDto,
                                                @PathVariable Long id,
                                                BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeCountryOfOrigin(id, parentDto));
    }

    @PutMapping("/changeSpokenLanguage/{id}")
    public ResponseEntity changeSpokenLanguage(@Valid @RequestBody ParentDto parentDto,
                                               @PathVariable Long id,
                                               BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeSpokenLanguage(id, parentDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteParentById(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteParentById(id));
    }


}
