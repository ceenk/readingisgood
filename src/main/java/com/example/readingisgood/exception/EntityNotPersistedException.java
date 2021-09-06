package com.example.readingisgood.exception;

public class EntityNotPersistedException extends Exception {

    private static final long serialVersionUID = 4235813513513075756L;

    public EntityNotPersistedException(final String message) {
        super(message);
    }
}
