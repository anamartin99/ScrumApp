package com.example.ScrumApp2.controller;

import com.example.ScrumApp2.model.User;
import com.example.ScrumApp2.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public List<User> getAllUser() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User addUserService(@RequestBody User newUser) {
        return userService.createUser(newUser);
    }

    @PutMapping(path = "/{id}")
    public void updateUser(@RequestBody User user, @PathVariable Long id) {
        userService.updateUser(id, user);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }
}