package com.mystery.core.exception;

import java.io.IOException;

public class ToDoException extends RuntimeException {

    public ToDoException() {
        super();
    }

    public ToDoException(String message) {
        super(message);
    }
    
    public ToDoException(IOException e) {
        initCause(e);
    }
    
    public ToDoException(String message, IOException e) {
        super(message);
        initCause(e);
    }
}
