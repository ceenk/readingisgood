package com.example.readingisgood.controller;

import com.example.readingisgood.constant.SecurityConstants;
import com.example.readingisgood.exception.EntityNotPersistedException;
import com.example.readingisgood.exception.InsufficientAccessException;
import com.example.readingisgood.model.request.BookRequest;
import com.example.readingisgood.model.request.StockUpdateRequest;
import com.example.readingisgood.model.response.BookResponse;
import com.example.readingisgood.service.BookService;
import com.example.readingisgood.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final JwtUtil jwtUtil;

    @PostMapping("/create")
    public ResponseEntity<BookResponse> persist(@RequestHeader (name= SecurityConstants.AUTHORIZATION_HEADER_KEY) String authorizationHeader,
                                                @Valid @RequestBody final BookRequest bookRequest)
            throws EntityNotPersistedException, InsufficientAccessException {

        final String username = jwtUtil.extractUsernameFromAuthorizationHeader(authorizationHeader);

        if (!SecurityConstants.ADMIN_USERNAME.equalsIgnoreCase(username)) {
            throw new InsufficientAccessException("Only admin can add book");
        }

        return ResponseEntity.ok(bookService.save(bookRequest));
    }

    @PutMapping("/stock")
    public ResponseEntity<BookResponse> updateStock(@RequestHeader (name= SecurityConstants.AUTHORIZATION_HEADER_KEY) String authorizationHeader,
                                                    @Valid @RequestBody final StockUpdateRequest stockUpdateRequest)
            throws EntityNotPersistedException, InsufficientAccessException {

        final String username = jwtUtil.extractUsernameFromAuthorizationHeader(authorizationHeader);

        if (!SecurityConstants.ADMIN_USERNAME.equalsIgnoreCase(username)) {
            throw new InsufficientAccessException("Only admin can update stock");
        }

        return ResponseEntity.ok(bookService.updateStock(stockUpdateRequest));
    }
}
