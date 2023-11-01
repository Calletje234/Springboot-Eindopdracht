package com.example.SchoolOpdracht.SchoolOpdracht.controller;


import com.example.SchoolOpdracht.SchoolOpdracht.repository.RoleRepository;
import com.example.SchoolOpdracht.SchoolOpdracht.dto.RoleDto;
import com.example.SchoolOpdracht.SchoolOpdracht.model.Role;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleRepository repos;

    public RoleController(RoleRepository repos) {
        this.repos = repos;
    }
    @GetMapping("")
    public Iterable<Role> getAllRoles() {
        return repos.findAll();
    }

    @PostMapping("")
    public String createRole(@RequestBody RoleDto role) {
        Role newRole = new Role();
        newRole.setRolename(role.rolename);
        repos.save(newRole);

        return "Done";
    }
}
