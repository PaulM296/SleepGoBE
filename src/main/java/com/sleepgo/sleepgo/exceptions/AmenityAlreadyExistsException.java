package com.sleepgo.sleepgo.exceptions;

import com.sleepgo.sleepgo.repositories.AmenityRepository;

public class AmenityAlreadyExistsException extends Exception {
    public AmenityAlreadyExistsException(String message) {
        super(message);
    }
}
