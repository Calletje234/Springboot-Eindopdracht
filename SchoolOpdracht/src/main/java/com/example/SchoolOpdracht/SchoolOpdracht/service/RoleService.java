package com.example.SchoolOpdracht.SchoolOpdracht.service;

import com.example.SchoolOpdracht.SchoolOpdracht.dto.RoleDto;
import com.example.SchoolOpdracht.SchoolOpdracht.exceptions.RecordNotFoundException;
import com.example.SchoolOpdracht.SchoolOpdracht.exceptions.RoleAlreadyExistsException;
import com.example.SchoolOpdracht.SchoolOpdracht.exceptions.RoleHasUsersException;
import com.example.SchoolOpdracht.SchoolOpdracht.helpers.Util;
import com.example.SchoolOpdracht.SchoolOpdracht.model.Role;
import com.example.SchoolOpdracht.SchoolOpdracht.model.User;
import com.example.SchoolOpdracht.SchoolOpdracht.repository.RoleRepository;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository repos;
    private final UserService userService;

    public RoleService(RoleRepository r, UserService service) {
        this.repos = r;
        this.userService = service;
    }

    public Long createNewRole(RoleDto roleDto) {
        Optional<Role> existingRole = repos.findByrolename(roleDto.rolename);
        if (existingRole.isPresent()) {
            throw new RoleAlreadyExistsException("Role with name: " + roleDto.rolename + " already exists in database");
        }
        Role newRole = new Role();
        newRole.setRolename(roleDto.rolename);
        Role savedRole = repos.save(newRole);
        return savedRole.getId();
    }

    public Iterable<RoleDto> getAllRoles() {
        Iterable<Role> allRoles = repos.findAll();
        ArrayList<RoleDto> resultLit = new ArrayList<>();
        for (Role r : allRoles) {
            resultLit.add(createReturnDto(r));
        }
        return resultLit;
    }

    public void deleteRole(Long id) {
        Role foundRole = getRoleById(id);
        Collection<User> usersWithRole = foundRole.getUsers();
        if(!usersWithRole.isEmpty()) {
            throw new RoleHasUsersException("Can't delete role with name: " + foundRole.getRolename() + ". Role has users in it.");
        } else {
            repos.deleteById(id);
        }
    }

    public Role getRoleById(Long id) {
        return Util.checkAndFindById(id, repos)
                .orElseThrow(() -> new RecordNotFoundException("Role with id: " + id + " not found"));
    }

    public RoleDto createReturnDto(Role r) {
        RoleDto returnDto = new RoleDto();
        returnDto.rolename = r.getRolename();
        return returnDto;
    }
}
