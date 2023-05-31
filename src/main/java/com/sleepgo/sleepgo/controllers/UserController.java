package com.sleepgo.sleepgo.controllers;

import com.sleepgo.sleepgo.exceptions.HotelNotFoundException;
import com.sleepgo.sleepgo.exceptions.InvalidTokenException;
import com.sleepgo.sleepgo.exceptions.UserNotFoundException;
import com.sleepgo.sleepgo.models.AuthenticationSessionModel;
import com.sleepgo.sleepgo.models.UserModel;
import com.sleepgo.sleepgo.repositories.UserRepository;
import com.sleepgo.sleepgo.services.AuthenticationService;
import com.sleepgo.sleepgo.services.ReservationService;
import com.sleepgo.sleepgo.services.ReviewService;
import com.sleepgo.sleepgo.services.UserService;
import org.apache.catalina.User;
import org.hibernate.SessionException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/user-account")
@CrossOrigin(origins = "*")
public class UserController {
    @Resource
    private UserRepository userRepository;
    @Resource
    private UserService userService;

    @Resource
    private AuthenticationService authenticationService;

    @Resource
    private ReservationService reservationService;

    @Resource
    private ReviewService reviewService;

    public UserController(UserRepository userRepository, UserService userService, AuthenticationService authenticationService, ReservationService reservationService, ReviewService reviewService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.reservationService = reservationService;
        this.reviewService = reviewService;
    }

    @GetMapping("/{username}")
    public UserModel getUserByUsername(@PathVariable("username") String username, @RequestHeader("custom-token") String token) throws UserNotFoundException, InvalidTokenException {
        if(!authenticationService.checkAuthenticationSessionExists(username)) {
            throw new SessionException("Session does not exist");
        }
        if(authenticationService.checkAuthenticationToken(username, token)) {
            return userService.getByUsername(username);
        }
        throw new InvalidTokenException("The token is invalid");
    }

    @PutMapping("/{username}")
    public UserModel updateUser(@PathVariable("username") String username, @RequestHeader("custom-token") String token , @RequestBody UserModel updatedUser) throws UserNotFoundException, InvalidTokenException {
        if(!authenticationService.checkAuthenticationSessionExists(username)) {
            throw new SessionException("Session does not exist");
        }
        if(authenticationService.checkAuthenticationToken(username, token)) {
            return userService.updateUser(username, updatedUser);
        }
        throw new InvalidTokenException("The token is invalid");
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable("id") int userId) throws UserNotFoundException {
        userService.deleteUserById(userId);
    }

    @DeleteMapping("/{username}")
    public void deleteUserByUsername(@PathVariable("username") String username, @RequestHeader("custom-token") String token) throws UserNotFoundException, InvalidTokenException {
        if (!authenticationService.checkAuthenticationSessionExists(username)) {
            throw new SessionException("Session does not exist");
        }
        if (authenticationService.checkAuthenticationToken(username, token)) {
            userService.deleteUserByUsername(username);
        } else {
            throw new InvalidTokenException("The token is invalid");
        }
    }

}
