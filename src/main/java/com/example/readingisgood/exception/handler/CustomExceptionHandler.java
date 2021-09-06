package com.example.readingisgood.exception.handler;

import com.example.readingisgood.exception.*;
import com.example.readingisgood.model.error.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                                  final HttpHeaders headers,
                                                                  final HttpStatus status,
                                                                  final WebRequest request) {

        final var errorResponse = ErrorResponse.builder()
                .statusCode(status.value())
                .timestamp(LocalDate.now())
                .message(status.getReasonPhrase())
                .description(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage())
                .build();

        return super.handleExceptionInternal(ex, errorResponse, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex,
                                                                  final HttpHeaders headers,
                                                                  final HttpStatus status,
                                                                  final WebRequest request) {

        final var errorResponse = ErrorResponse.builder()
                .statusCode(status.value())
                .timestamp(LocalDate.now())
                .message(status.getReasonPhrase())
                .description(ex.getMessage())
                .build();

        return super.handleExceptionInternal(ex, errorResponse, headers, status, request);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleGlobalException(final Exception ex, final WebRequest request) {
        final var errorResponse = ErrorResponse.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(LocalDate.now())
                .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .description("Something went wrong!")
                .build();

        return super.handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = { EntityAlreadyExistException.class})
    protected ResponseEntity<Object> handleCustomerAlreadyExists(final EntityAlreadyExistException ex,
                                                                 final WebRequest request) {

        final var errorResponse = ErrorResponse.builder()
                .statusCode(HttpStatus.CONFLICT.value())
                .timestamp(LocalDate.now())
                .message("Data already exists")
                .description(ex.getMessage())
                .build();

        return super.handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = { EntityNotPersistedException.class})
    protected ResponseEntity<Object> handleCustomerNotInsertedException(final EntityNotPersistedException ex,
                                                                        final WebRequest request) {

        final var errorResponse = ErrorResponse.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(LocalDate.now())
                .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .description(ex.getMessage())
                .build();

        return super.handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = { CustomerNotFoundException.class})
    protected ResponseEntity<Object> handleCustomerNotFoundException(final CustomerNotFoundException ex,
                                                                     final WebRequest request) {
        final var errorResponse = ErrorResponse.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDate.now())
                .message(HttpStatus.NOT_FOUND.getReasonPhrase())
                .description(ex.getMessage())
                .build();
        return super.handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = { InsufficientStockCountException.class})
    protected ResponseEntity<Object> handleInsufficientStockCountException(final InsufficientStockCountException ex,
                                                                           final WebRequest request) {
        final var errorResponse = ErrorResponse.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDate.now())
                .message(HttpStatus.NOT_FOUND.getReasonPhrase())
                .description(ex.getMessage())
                .build();
        return super.handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = { OrderNotFoundException.class})
    protected ResponseEntity<Object> handleOrderNotFoundException(final OrderNotFoundException ex,
                                                                  final WebRequest request) {
        final var errorResponse = ErrorResponse.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDate.now())
                .message(HttpStatus.NOT_FOUND.getReasonPhrase())
                .description(ex.getMessage())
                .build();
        return super.handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = { OrderContentNotFoundException.class})
    protected ResponseEntity<Object> handleOrderContentNotFoundException(final OrderContentNotFoundException ex,
                                                                         final WebRequest request) {
        final var errorResponse = ErrorResponse.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDate.now())
                .message(HttpStatus.NOT_FOUND.getReasonPhrase())
                .description(ex.getMessage())
                .build();
        return super.handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = { InsufficientAccessException.class})
    protected ResponseEntity<Object> handleInsufficientAccessException(final InsufficientAccessException ex,
                                                                       final WebRequest request) {
        final var errorResponse = ErrorResponse.builder()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .timestamp(LocalDate.now())
                .message(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .description(ex.getMessage())
                .build();
        return super.handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }
}
