package com.example.SchoolOpdracht.SchoolOpdracht.controller;


import com.example.SchoolOpdracht.SchoolOpdracht.dto.UserDto;
import com.example.SchoolOpdracht.SchoolOpdracht.helpers.Util;
import com.example.SchoolOpdracht.SchoolOpdracht.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService s) {
        this.service = s;
    }

    @PostMapping("")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDto userDto, BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        
        Long createdId = service.createNewUser(userDto);

        URI uri = URI.create(
                ServletUriComponentsBuilder.
                        fromCurrentContextPath().
                        path("/users/" + createdId).toUriString());
        return ResponseEntity.created(uri).body("User created");
    }

    @GetMapping("")
    public ResponseEntity<Iterable<UserDto>> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getUserById(id));
    }

    @PutMapping("/{id}/addRoles")
    public ResponseEntity<UserDto> addRoleToUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto, BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity(Util.createErrorMessage(br), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.addRoleToUser(id, userDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        service.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
