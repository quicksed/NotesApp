package com.notes.exception;

public class EmailAlreadyTakenException extends RuntimeException{

    public EmailAlreadyTakenException() {
        super("Данный email уже занят");
    }
}
