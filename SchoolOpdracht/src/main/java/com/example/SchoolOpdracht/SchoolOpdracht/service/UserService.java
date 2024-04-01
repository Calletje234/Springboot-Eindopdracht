package com.example.SchoolOpdracht.SchoolOpdracht.service;

import com.example.SchoolOpdracht.SchoolOpdracht.dto.UserDto;
import com.example.SchoolOpdracht.SchoolOpdracht.exceptions.RecordNotFoundException;
import com.example.SchoolOpdracht.SchoolOpdracht.exceptions.UserAlreadyExistsException;
import com.example.SchoolOpdracht.SchoolOpdracht.helpers.Util;
import com.example.SchoolOpdracht.SchoolOpdracht.model.User;
import com.example.SchoolOpdracht.SchoolOpdracht.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repos;

    public UserService(UserRepository r) {
        this.repos = r;
    }

    public Long createNewUser(UserDto userDto) {
        // Check of de user al bestaat
        Optional<User> existingUser = repos.findByUsername(userDto.username);
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("User with username: " + userDto.username + " already exists");
        }

        User newUser = new User();
        newUser.setUsername(userDto.username);
        newUser.setPassword(userDto.password);
        newUser.setRoles(userDto.roles);

        User savedUser = repos.save(newUser);
        return savedUser.getId();
    }

    public Iterable<UserDto> getAllUsers() {
        Iterable<User> allUsers = repos.findAll();
        ArrayList<UserDto> resultList = new ArrayList<>();
        for (User u: allUsers){
            resultList.add(createUserReturnDto(u));
        }
        return resultList;
    }

    public Iterable<UserDto> getAllUsersWithSpecificRole(String roleName) {
        List<User> usersWithRole = repos.findUsersByRoles(roleName);
        List<UserDto> userList = new ArrayList<>();
        for (User u : usersWithRole) {
            userList.add(createUserReturnDto(u));
        }
        return userList;
    }

    public UserDto addRoleToUser(Long id, UserDto userDto) {
        User requestedUser = getUserFromRepository(id);
        requestedUser.setRoles(userDto.roles);
        repos.save(requestedUser);
        return createUserReturnDto(requestedUser);
    }

    public UserDto getUserById(Long id) {
        User requestedUser = getUserFromRepository(id);
        return createUserReturnDto(requestedUser);
    }

    public void deleteUserById(Long id) {
        User deletedUser = getUserFromRepository(id);
        repos.deleteById(id);
    }

    public User getUserFromRepository(Long id) {
        return Util.checkAndFindById(id, repos)
                .orElseThrow(() -> new RecordNotFoundException("User Record not found with id: " + id));
    }

    public UserDto createUserReturnDto(User user) {
        UserDto userDto = new UserDto();
        userDto.username = user.getUsername();
        userDto.password = user.getPassword();
        userDto.userType = user.getUserType();
        userDto.userTypeId = user.getUserTypeId();
        userDto.roles = user.getRoles();
        return userDto;
    }
}
