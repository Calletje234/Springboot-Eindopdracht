package com.example.SchoolOpdracht.SchoolOpdracht.controller;


import com.example.SchoolOpdracht.SchoolOpdracht.dto.AfwezigDto;
import com.example.SchoolOpdracht.SchoolOpdracht.helpers.Util;
import com.example.SchoolOpdracht.SchoolOpdracht.service.AfwezigService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/afwezig")
public class AfwezigController {
    private final AfwezigService service;

    public AfwezigController(AfwezigService s) {this.service = s;}

    @GetMapping("")
    public ResponseEntity<Iterable<AfwezigDto>> getAllAfwezig() {
        return ResponseEntity.ok(service.getAllAfwezig());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AfwezigDto> getAfwezigById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAfwezigById(id));
    }

    @PostMapping("")
    public ResponseEntity<String> createAfwezig(@Valid @RequestBody AfwezigDto afwezigDto, BindingResult br){
        Long createdId = service.createAfwezigPeriod(afwezigDto);

        URI uri = URI.create(
                ServletUriComponentsBuilder.
                        fromCurrentContextPath().
                        path("/afwezig/" + createdId).toUriString());
        return ResponseEntity.created(uri).body("Afwezigheid period created");
    }

    @PutMapping("/changeReason/{id}")
    public ResponseEntity changeAfwezigReason(@Valid @RequestBody AfwezigDto afwezigDto,
                                                      @PathVariable Long id,
                                                      BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeReasonAfwezig(id, afwezigDto));

    }

    @PutMapping("/changeEndingDate/{id}")
    public ResponseEntity changeEndingDate(@Valid @RequestBody AfwezigDto afwezigDto,
                                           @PathVariable Long id,
                                           BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeEndingDate(id, afwezigDto));
    }

    @PutMapping("/changeStartingDate/{id}")
    public ResponseEntity changeStartingDate(@Valid @RequestBody AfwezigDto afwezigDto,
                                             @PathVariable Long id,
                                             BindingResult br) {
        if(br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeStartingDate(id, afwezigDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AfwezigDto> deleteAfwezigById(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteAfwezigById(id));
    }
}
