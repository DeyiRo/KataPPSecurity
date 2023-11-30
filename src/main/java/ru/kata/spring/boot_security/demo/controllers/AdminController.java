package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entityes.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;


@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setServices(UserService userService, RoleService roleService) {

        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String getUsersList(Model model) {
        model.addAttribute("usersList", userService.showAllUsers());
        return "admin";
    }

    @GetMapping("/new")
    public String createNewUser(Model model) {
        model.addAttribute("userForm", new User());
        return "new";
    }

    @PostMapping("/new")
    public String addNewUserToDB(@ModelAttribute("userForm") User userForm, Model model) {

        if (!userService.saveUser(userForm)) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "new";
        }
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        model.addAttribute("listRoles", roleService.getAllRoles());
        return "edit";
    }

    @PutMapping("/edit")
    public String saveUserChangesToDB(@Valid User user, BindingResult bindingResult,
                                      @RequestParam("listRoles") ArrayList<Long> roles) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        userService.updateUser(user, roleService.findRoles(roles));
        return "redirect:/admin";
    }


    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin-page")
    public String showUserDetails(Model model, Principal principal) {
        model.addAttribute("user", userService.loadUserByUsername(principal.getName()));
        return "user";
    }

}
