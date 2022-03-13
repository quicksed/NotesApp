package com.notes.controller;

import com.notes.exception.EmailAlreadyTakenException;
import com.notes.exception.EmptyNoteException;
import com.notes.exception.NotValidEmailException;
import com.notes.exception.PasswordsNotEqualsException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AdviceController {

    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException exception, Model model) {
        model.addAttribute("message", exception.getMessage());

        return "error";
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EmailAlreadyTakenException.class)
    public String handleEmailAlreadyTakenException(EmailAlreadyTakenException exception, Model model) {
        model.addAttribute("message", exception.getMessage());

        return "error";
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(PasswordsNotEqualsException.class)
    public String handlePasswordsNotEqualsException(PasswordsNotEqualsException exception, Model model) {
        model.addAttribute("message", exception.getMessage());

        return "error";
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(NotValidEmailException.class)
    public String handleNotValidEmailException(NotValidEmailException exception, Model model) {
        model.addAttribute("message", exception.getMessage());

        return "error";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmptyNoteException.class)
    public String handleEmptyNoteException(EmptyNoteException exception, Model model) {
        model.addAttribute("message", exception.getMessage());

        return "error";
    }
}
