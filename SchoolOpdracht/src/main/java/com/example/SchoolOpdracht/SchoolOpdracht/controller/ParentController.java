package com.example.SchoolOpdracht.SchoolOpdracht.controller;


import com.example.SchoolOpdracht.SchoolOpdracht.dto.FileDto;
import com.example.SchoolOpdracht.SchoolOpdracht.dto.ParentDto;
import com.example.SchoolOpdracht.SchoolOpdracht.helpers.Util;
import com.example.SchoolOpdracht.SchoolOpdracht.service.ParentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


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

    @GetMapping("/{id}/files")
    public ResponseEntity<List<FileDto>> getAssociatedFiles(@PathVariable Long id) {
        return ResponseEntity.ok(service.getFilesByParent(id));
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

    @PutMapping("/{id}/firstName")
    public ResponseEntity changeFirstName(@Valid @RequestBody ParentDto parentDto,
                                          @PathVariable Long id,
                                          BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeFirstName(id, parentDto));
    }

    @PutMapping("/{id}/lastName")
    public ResponseEntity changeLastName(@Valid @RequestBody ParentDto parentDto,
                                         @PathVariable Long id,
                                         BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeLastName(id, parentDto));
    }

    @PutMapping("/{id}/phoneNumber")
    public ResponseEntity changePhoneNumber(@Valid @RequestBody ParentDto parentDto,
                                            @PathVariable Long id,
                                            BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changePhoneNumber(id, parentDto));
    }

    @PutMapping("/{id}/address")
    public ResponseEntity changeAddress(@Valid @RequestBody ParentDto parentDto,
                                        @PathVariable Long id,
                                        BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeAddress(id, parentDto));
    }

    @PutMapping("/{id}/countryOfOrigin")
    public ResponseEntity changeCountryOfOrigin(@Valid @RequestBody ParentDto parentDto,
                                                @PathVariable Long id,
                                                BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeCountryOfOrigin(id, parentDto));
    }

    @PutMapping("/{id}/spokenLanguage")
    public ResponseEntity changeSpokenLanguage(@Valid @RequestBody ParentDto parentDto,
                                               @PathVariable Long id,
                                               BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeSpokenLanguage(id, parentDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteParentById(@PathVariable Long id) {
        service.deleteParentById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
