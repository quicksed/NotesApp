package com.notes.exception;

public class PasswordsNotEqualsException extends RuntimeException {

    public PasswordsNotEqualsException() {
        super("Пароли не совпадают");
    }
}
