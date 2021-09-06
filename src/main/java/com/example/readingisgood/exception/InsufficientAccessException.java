package com.example.readingisgood.exception;

public class InsufficientAccessException extends Exception {

    private static final long serialVersionUID = 5533834010592858195L;

    public InsufficientAccessException(final String message) {
        super(message);
    }
}
