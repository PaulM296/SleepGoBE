package com.sleepgo.sleepgo.handlers;

import com.sleepgo.sleepgo.exceptions.*;
import com.sleepgo.sleepgo.models.RestExceptionModel;
import org.hibernate.SessionException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = {UserNotFoundException.class, ReviewNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    RestExceptionModel notFoundExceptionHandler(Exception e) {
        return new RestExceptionModel(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = {SessionException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    RestExceptionModel badRequestExceptionHandler(Exception e) {
        return new RestExceptionModel(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = {InvalidCredentialsException.class, InvalidTokenException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    RestExceptionModel forbiddenExceptionHandler(Exception e) {
        return new RestExceptionModel(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = {UserAlreadyExistsException.class, EmailAlreadyExistsException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    RestExceptionModel conflictExceptionHandler(Exception e) {
        return new RestExceptionModel(e.getMessage());
    }
}
