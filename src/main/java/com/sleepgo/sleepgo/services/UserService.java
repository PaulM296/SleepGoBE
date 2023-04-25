package com.sleepgo.sleepgo.services;

import com.sleepgo.sleepgo.exceptions.UserNotFoundException;
import com.sleepgo.sleepgo.models.UserModel;
import com.sleepgo.sleepgo.repositories.UserRepository;
import jakarta.annotation.Resource;
import org.apache.catalina.User;

import java.util.List;

public class UserService {

    @Resource
    UserRepository userRepository;
    public UserModel getByUsername(String username) throws UserNotFoundException {
        List<UserModel> result = userRepository.findByUsername(username);
        if(result.size() == 0) {
            throw new UserNotFoundException(String.format("The user with the %s username does not exist", username));
        }
        return result.get(0);
    }
    public UserModel saveUser(UserModel user) {
        return userRepository.save(user);
    }
}
