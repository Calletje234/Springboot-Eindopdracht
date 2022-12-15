package com.example.SchoolOpdracht.controller;


import com.example.SchoolOpdracht.dto.AfwezigDto;
import com.example.SchoolOpdracht.service.AfwezigService;
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
        return ResponseEntity.ok(service.getAfwezigeById(id));
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
}
