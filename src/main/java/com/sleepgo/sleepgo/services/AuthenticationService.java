package com.sleepgo.sleepgo.services;

import com.sleepgo.sleepgo.exceptions.UserNotFoundException;
import com.sleepgo.sleepgo.models.AmenitiesModel;
import com.sleepgo.sleepgo.models.AuthenticationSessionModel;
import com.sleepgo.sleepgo.models.UserModel;
import com.sleepgo.sleepgo.repositories.AuthenticationSessionRepository;
import com.sleepgo.sleepgo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {

    @Resource
    UserRepository userRepository;

    @Resource
    AuthenticationSessionRepository authenticationSessionRepository;

    @Value("${sleepgo.auth.expiration}")
    String expirationDeltaString;

    public String encodePassword(String password, String saltString) {
        byte[] hash = new byte[0];
        try {
            byte[] salt = saltString.getBytes(StandardCharsets.UTF_8);

            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            hash = factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(hash);
    }

    public boolean checkAuthenticationToken(String username, String token) {
        List<AuthenticationSessionModel> activeSessions = authenticationSessionRepository.findAll().stream()
                .filter(x ->(x.getUsername().equals(username) && x.getToken().equals(token)))
                .collect(Collectors.toList());
        return activeSessions.size() != 0;
    }

    public boolean checkAuthenticationSessionExists(String username) {
        List<AuthenticationSessionModel> activeSessions = authenticationSessionRepository.findAll().stream()
                .filter(x -> (x.getUsername().equals(username)))
                .collect(Collectors.toList());
        return activeSessions.size() != 0;
    }

    public void saveAuthenticationSession(AuthenticationSessionModel authentication) {
        authenticationSessionRepository.save(authentication);
    }

    public void deleteSession(String username, String token) throws UserNotFoundException {
        AuthenticationSessionModel session = getByUsernameAndToken(username, token);
        authenticationSessionRepository.deleteById(session.getId());
    }

    @Scheduled(fixedRateString = "${sleepgo.auth.refresh.rate}", initialDelay = 1000)
    public void cleanupAuthenticationSessions() throws UserNotFoundException {
        List<AuthenticationSessionModel> authenticationSessions = authenticationSessionRepository.findAll();
        for(AuthenticationSessionModel s : authenticationSessions) {
            if((System.currentTimeMillis() - s.getCreatedAt().getTime()) >= Long.parseLong(expirationDeltaString)) {
                deleteSession(s.getUsername(), s.getToken());
            }
        }
    }

    private AuthenticationSessionModel getByUsernameAndToken(String username, String token) throws UserNotFoundException {
        List<AuthenticationSessionModel> result = authenticationSessionRepository.findByUsername(username)
                .stream().filter(x -> x.getToken().equals(token))
                .collect(Collectors.toList());
        if(result.size() == 0) {
            throw new UserNotFoundException(String.format("The user %s does not exist", username));
        }
        return result.get(0);
    }

    public boolean checkUsernameAlreadyExists(String username) {
        List<UserModel> resultUsername = userRepository.findByUsername(username);
        return resultUsername.size() != 0;
    }

    public boolean checkEmailAlreadyExists(String email) {
        List<UserModel> resultEmail = userRepository.findByEmail(email);
        return resultEmail.size() != 0;
    }

    public String getLoggedInUsername(String token) throws UserNotFoundException {
        List<AuthenticationSessionModel> activeSessions = authenticationSessionRepository.findAll().stream()
                .filter(x -> x.getToken().equals(token))
                .collect(Collectors.toList());

        if (activeSessions.isEmpty()) {
            throw new UserNotFoundException("User not found for the given token");
        }

        return activeSessions.get(0).getUsername();
    }

}
