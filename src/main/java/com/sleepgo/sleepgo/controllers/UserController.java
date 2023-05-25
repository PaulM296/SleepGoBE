package com.sleepgo.sleepgo.controllers;

import com.sleepgo.sleepgo.exceptions.UserNotFoundException;
import com.sleepgo.sleepgo.models.UserModel;
import com.sleepgo.sleepgo.repositories.UserRepository;
import com.sleepgo.sleepgo.services.UserService;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-account")
public class UserController {

    private UserRepository userRepository;
    private UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserModel getUserById(@PathVariable("id") int userId) throws UserNotFoundException {
        UserModel user = userService.getUserById(userId);
        return user;
    }

    @PutMapping("/{id}")
    public UserModel updateUser(@PathVariable("id") int userId, @RequestBody UserModel updatedUser) throws UserNotFoundException {
        UserModel user = userService.updateUser(userId, updatedUser);
        return user;
    }

}
