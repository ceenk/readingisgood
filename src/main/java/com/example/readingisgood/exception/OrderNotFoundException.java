package com.example.readingisgood.exception;

public class OrderNotFoundException extends Exception {

    private static final long serialVersionUID = -2569918696654857398L;

    public OrderNotFoundException(final String message) {
        super(message);
    }
}
