package com.example.readingisgood.exception;

public class EntityAlreadyExistException extends Exception {

    private static final long serialVersionUID = 1933111555052362864L;

    public EntityAlreadyExistException(final String message) {
        super(message);
    }
}
