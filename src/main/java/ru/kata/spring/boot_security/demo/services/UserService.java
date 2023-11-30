package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.entityes.Role;
import ru.kata.spring.boot_security.demo.entityes.User;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);

    @Override
    UserDetails loadUserByUsername(String username);

    User findUserById(Long userId);

    List<User> showAllUsers();

    boolean saveUser(User user);

    void updateUser(User user, Set<Role> roles);

    boolean deleteUser(Long userId);


}
