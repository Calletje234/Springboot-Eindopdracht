package com.example.SchoolOpdracht.SchoolOpdracht.controller;

import com.example.SchoolOpdracht.SchoolOpdracht.dto.FileDto;
import com.example.SchoolOpdracht.SchoolOpdracht.dto.OpmerkingenDto;
import com.example.SchoolOpdracht.SchoolOpdracht.helpers.Util;
import com.example.SchoolOpdracht.SchoolOpdracht.service.OpmerkingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

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

    @GetMapping("/{id}/files")
    public ResponseEntity<List<FileDto>> getAssociatedFiles(@PathVariable Long id) {
        return ResponseEntity.ok(service.getFileByOpmerking(id));
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

    @PutMapping("/{id}/Opmerking")
    public ResponseEntity changeOpmerking(@Valid @RequestBody OpmerkingenDto opmerkingenDto,
                                          @PathVariable Long id,
                                          BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeOpmerking(id, opmerkingenDto));
    }

    @PutMapping("/{id}/contactDate")
    public ResponseEntity changeContactDate(@Valid @RequestBody OpmerkingenDto opmerkingenDto,
                                            @PathVariable Long id,
                                            BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.changeContactDate(id, opmerkingenDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOpmerkingById(@PathVariable Long id) {
        service.deleteOpmerkingById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
