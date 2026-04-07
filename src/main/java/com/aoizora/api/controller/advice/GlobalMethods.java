package com.aoizora.api.controller.advice;

import com.aoizora.service.exception.PetNotFoundException;
import com.aoizora.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalMethods {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User not found")
    public void userNotFound() throws NoHandlerFoundException {

    }

    @ExceptionHandler(PetNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Pet not found")
    public void petNotFound() throws NoHandlerFoundException {

    }

    @ModelAttribute("menuPlayUrl")
    public String menuPlayUrl(@Value("${virtualpets.play.url}") String playUrl) {
        return playUrl;
    }
}
