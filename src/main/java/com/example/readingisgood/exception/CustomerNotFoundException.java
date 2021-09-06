package com.example.readingisgood.exception;

public class CustomerNotFoundException extends Exception {

    private static final long serialVersionUID = 6324919037777178841L;

    public CustomerNotFoundException(final String message) {
        super(message);
    }
}
