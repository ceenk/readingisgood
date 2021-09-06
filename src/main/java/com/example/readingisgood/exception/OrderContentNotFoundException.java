package com.example.readingisgood.exception;

public class OrderContentNotFoundException extends Exception {

    private static final long serialVersionUID = -6594063775008619658L;

    public OrderContentNotFoundException(final String message) {
        super(message);
    }
}
