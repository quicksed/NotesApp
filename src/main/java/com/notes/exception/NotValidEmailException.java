package com.notes.exception;

public class NotValidEmailException extends RuntimeException {

    public NotValidEmailException() {
        super("Невалидный email");
    }
}
