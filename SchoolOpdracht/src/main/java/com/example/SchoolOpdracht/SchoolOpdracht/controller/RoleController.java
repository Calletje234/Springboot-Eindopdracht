package com.example.SchoolOpdracht.SchoolOpdracht.controller;


import com.example.SchoolOpdracht.SchoolOpdracht.helpers.Util;
import com.example.SchoolOpdracht.SchoolOpdracht.repository.RoleRepository;
import com.example.SchoolOpdracht.SchoolOpdracht.dto.RoleDto;
import com.example.SchoolOpdracht.SchoolOpdracht.model.Role;

import com.example.SchoolOpdracht.SchoolOpdracht.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService service;

    public RoleController(RoleService s ) {
        this.service = s;
    }
    @GetMapping("")
    public Iterable<RoleDto> getAllRoles() {
        return service.getAllRoles();
    }

    @PostMapping("")
    public ResponseEntity<String> createRole(@Valid @RequestBody RoleDto roleDto, BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity<>(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }

        Long createdId = service.createNewRole(roleDto);

        URI uri = URI.create(
                ServletUriComponentsBuilder.
                        fromCurrentContextPath().
                        path("/users/" + createdId).toUriString());
        return ResponseEntity.created(uri).body("Role created");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable Long id){
        service.deleteRole(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
