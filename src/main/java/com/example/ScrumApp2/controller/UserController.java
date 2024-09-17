package com.example.ScrumApp2.controller;

import com.example.ScrumApp2.model.User;
import com.example.ScrumApp2.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping(path = "/user")
    public List<User> getAllUser() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "/user/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @PostMapping(path = "/user")
    public User addUserService(@RequestBody User newUser) {
        return userService.createUser(newUser);
    }

    @PutMapping(path = "/user/{id}")
    public void updateUser(@RequestBody User user, @PathVariable Long id) {
        userService.updateUser(id, user);
    }

    @DeleteMapping(path = "/user/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }
}
