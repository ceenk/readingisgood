package com.example.readingisgood.exception;

public class InsufficientStockCountException extends Exception {

    private static final long serialVersionUID = -7182933414245226422L;

    public InsufficientStockCountException(final String message) {
        super(message);
    }
}
