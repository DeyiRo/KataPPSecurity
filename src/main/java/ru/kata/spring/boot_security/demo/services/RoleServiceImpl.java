package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entityes.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public Set<Role> findRoles(List<Long> roles) {
        return new HashSet<>(roleRepository.findAllById(roles));
    }

    @Transactional(readOnly = true)
    public List<Role> getAllRoles() {

        return roleRepository.findAll().stream().toList();
    }

}
