package com.example.SchoolOpdracht.SchoolOpdracht.controller;


import com.example.SchoolOpdracht.SchoolOpdracht.exceptions.NoRoleFoundException;
import com.example.SchoolOpdracht.SchoolOpdracht.repository.RoleRepository;
import com.example.SchoolOpdracht.SchoolOpdracht.repository.UserRepository;
import com.example.SchoolOpdracht.SchoolOpdracht.dto.UserDto;
import com.example.SchoolOpdracht.SchoolOpdracht.model.Role;
import com.example.SchoolOpdracht.SchoolOpdracht.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private final UserRepository userRepos;
    private final RoleRepository roleRepos;
    private final PasswordEncoder encoder;

    public UserController(UserRepository userRepos, RoleRepository roleRepos, PasswordEncoder encoder) {
        this.userRepos = userRepos;
        this.roleRepos = roleRepos;
        this.encoder = encoder;
    }
    @PostMapping("/users")
    public String createUser(@RequestBody UserDto userDto) {
        User newUser = new User();
        newUser.setUsername(userDto.username);
        newUser.setPassword(encoder.encode(userDto.password));

        List<Role> userRoles = new ArrayList<>();
        for (String rolename : userDto.roles) {
            Optional<Role> or = roleRepos.findById(rolename);
            if(or.isEmpty()){
                throw new NoRoleFoundException("No role found with name: " + rolename);
            } else {
                userRoles.add(or.get());
            }
        }
        newUser.setRoles(userRoles);

        userRepos.save(newUser);

        return "Done";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
