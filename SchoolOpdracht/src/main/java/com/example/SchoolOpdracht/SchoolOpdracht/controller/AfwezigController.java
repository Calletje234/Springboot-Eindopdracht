package com.example.SchoolOpdracht.SchoolOpdracht.controller;


import com.example.SchoolOpdracht.SchoolOpdracht.dto.AfwezigDto;
import com.example.SchoolOpdracht.SchoolOpdracht.dto.FileDto;
import com.example.SchoolOpdracht.SchoolOpdracht.helpers.Util;
import com.example.SchoolOpdracht.SchoolOpdracht.service.AfwezigService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

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

    @GetMapping("/{id}/files")
    public ResponseEntity<List<FileDto>> getAssoicatedFiles(@PathVariable Long id) {
        return ResponseEntity.ok(service.getFileByAfwezigMelding(id));
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

    @PutMapping("/{id}/reason")
    public ResponseEntity changeAfwezigReason(@Valid @RequestBody AfwezigDto afwezigDto,
                                                      @PathVariable Long id,
                                                      BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeReasonAfwezig(id, afwezigDto));

    }

    @PutMapping("/{id}/endingDate")
    public ResponseEntity changeEndingDate(@Valid @RequestBody AfwezigDto afwezigDto,
                                           @PathVariable Long id,
                                           BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeEndingDate(id, afwezigDto));
    }

    @PutMapping("/{id}/startingDate")
    public ResponseEntity changeStartingDate(@Valid @RequestBody AfwezigDto afwezigDto,
                                             @PathVariable Long id,
                                             BindingResult br) {
        if(br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeStartingDate(id, afwezigDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAfwezigById(@PathVariable Long id) {
        service.deleteAfwezigById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
