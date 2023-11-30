package ru.kata.spring.boot_security.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entityes.Role;
import ru.kata.spring.boot_security.demo.entityes.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class DBInitilizer {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public DBInitilizer() {
    }

    @PostConstruct
    private void addUsersToDB() {
        User user1 = new User("Romul", "first", "$2a$12$Tk9bQjUbIGUPcaCoouQ05uj78BF/uRkK3S6IOxy9HwnWgUkBrEOya");
        User user2 = new User("Rem", "second", "$2a$12$Tk9bQjUbIGUPcaCoouQ05uj78BF/uRkK3S6IOxy9HwnWgUkBrEOya");
        Role role1 = new Role("ROLE_ADMIN");
        Role role2 = new Role("ROLE_USER");

        roleRepository.save(role1);
        roleRepository.save(role2);

        Set<Role> roles1 = new HashSet<>();
        Set<Role> roles2 = new HashSet<>();
        roles1.add(role1);
        roles1.add(role2);
        roles2.add(role2);

        user1.setRoles(roles1);
        user2.setRoles(roles2);


        userRepository.save(user1);
        userRepository.save(user2);
    }

}
