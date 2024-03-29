package com.sleepgo.sleepgo.services;

import com.sleepgo.sleepgo.exceptions.HotelNotFoundException;
import com.sleepgo.sleepgo.exceptions.UserNotFoundException;
import com.sleepgo.sleepgo.models.HotelModel;
import com.sleepgo.sleepgo.models.UserModel;
import com.sleepgo.sleepgo.repositories.UserRepository;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Resource
    private ReservationService reservationService;
    @Resource
    private ReviewService reviewService;
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

    public UserModel getUserById(int userId) throws UserNotFoundException {
        Optional<UserModel> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UserNotFoundException(String.format("User with ID %d not found", userId));
        }
    }

    public UserModel updateUser(String username, UserModel updatedUser) throws UserNotFoundException {
        UserModel user = getByUsername(username);
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());
        user.setPhoneNumber(updatedUser.getPhoneNumber());
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUserById(int userId) throws UserNotFoundException {
        reservationService.deleteReservationsByUserId(userId);

        reviewService.deleteReviewByUserId(userId);

        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        userRepository.delete(user);
    }

    @Transactional
    public void deleteUserByUsername(String username) throws UserNotFoundException {
        UserModel user = getByUsername(username);
        int userId = user.getId();

        reservationService.deleteReservationsByUserId(userId);
        reviewService.deleteReviewByUserId(userId);

        userRepository.deleteById(userId);
}
}
