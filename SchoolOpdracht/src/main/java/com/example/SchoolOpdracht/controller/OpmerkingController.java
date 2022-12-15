package com.example.SchoolOpdracht.controller;

import com.example.SchoolOpdracht.dto.OpmerkingenDto;
import com.example.SchoolOpdracht.service.OpmerkingService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/opmerking")
public class OpmerkingController {
    private final OpmerkingService service;

    public OpmerkingController(OpmerkingService s) {
        this.service = s;
    }

    @GetMapping("")
    public ResponseEntity<Iterable<OpmerkingenDto>> getAllOpmerkingen() {
        return ResponseEntity.ok(service.getAllOpmerkingen());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OpmerkingenDto> getOpmerkingById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOpmerkingById(id));
    }

    @PostMapping("")
    public ResponseEntity<String> createOpmerking(@Valid @RequestBody OpmerkingenDto opmerkingenDto, BindingResult br) {
        Long createdId = service.createOpmerking(opmerkingenDto);

        URI uri = URI.create(
                ServletUriComponentsBuilder.
                        fromCurrentContextPath().
                        path("/opmerking/" + createdId).toUriString());
        return ResponseEntity.created(uri).body("Opmerking created");
    }

}
