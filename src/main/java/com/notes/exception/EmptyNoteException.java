package com.notes.exception;

public class EmptyNoteException extends RuntimeException {

    public EmptyNoteException() {
        super("Заметка не может быть пустой");
    }
}
