package com.sleepgo.sleepgo.controllers;

import com.sleepgo.sleepgo.exceptions.*;
import com.sleepgo.sleepgo.models.AuthenticationSessionModel;
import com.sleepgo.sleepgo.models.AuthenticationTokenModel;
import com.sleepgo.sleepgo.models.UserModel;
import com.sleepgo.sleepgo.services.AuthenticationService;
import com.sleepgo.sleepgo.services.UserService;
import org.hibernate.SessionException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AuthenticationController {
    @Resource
    AuthenticationService autheticationService;

    @Resource
    UserService userService;

    @PostMapping("/login")
    public AuthenticationTokenModel login(@RequestBody Map<String, String> credentials) throws UserNotFoundException, InvalidCredentialsException {
        String username = credentials.get("username");
        String password = credentials.get("password");
        UserModel dbuser = userService.getByUsername(username);
        if(autheticationService.encodePassword(password, username).equals(dbuser.getPassword())) {
            AuthenticationSessionModel authenticationSession = new AuthenticationSessionModel();
            String token = UUID.randomUUID().toString();
            authenticationSession.setToken(token);
            authenticationSession.setUserId(dbuser.getId());
            authenticationSession.setUsername(dbuser.getUsername());
            authenticationSession.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            autheticationService.saveAuthenticationSession(authenticationSession);

            return new AuthenticationTokenModel(token);
        }
        throw new InvalidCredentialsException("The username or password you have entered are invalid");
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(@RequestParam String username, @RequestHeader("custom-token") String token) throws UserNotFoundException, InvalidTokenException, SessionException {
        if(!autheticationService.checkAuthenticationSessionExists(username)) {
            throw new SessionException("Session does not exist");
        }
        if(autheticationService.checkAuthenticationToken(username, token)) {
            autheticationService.deleteSession(username, token);
            return;
        }
        throw new InvalidTokenException("The token is invalid");
    }

    @PostMapping("/register")
    public UserModel addUser(@RequestBody UserModel user) throws UserAlreadyExistsException, EmailAlreadyExistsException {
        if(autheticationService.checkUsernameAlreadyExists(user.getUsername())) {
            throw new UserAlreadyExistsException(String.format("An account with this username %s already exists", user.getUsername()));
        }
        if (autheticationService.checkEmailAlreadyExists(user.getEmail())) {
            throw new EmailAlreadyExistsException(String.format("An account with this email %s already exists", user.getEmail()));
        }

        user.setPassword(autheticationService.encodePassword(user.getPassword(), user.getUsername()));

        return userService.saveUser(user);
    }
}
