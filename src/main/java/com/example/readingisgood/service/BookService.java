package com.example.readingisgood.service;

import com.example.readingisgood.entity.BookEntity;
import com.example.readingisgood.exception.EntityNotPersistedException;
import com.example.readingisgood.model.request.BookRequest;
import com.example.readingisgood.model.request.StockUpdateRequest;
import com.example.readingisgood.model.response.BookResponse;

import java.util.List;
import java.util.Set;

public interface BookService {

    BookResponse save(final BookRequest bookRequest) throws EntityNotPersistedException;
    BookResponse updateStock(final StockUpdateRequest stockUpdateRequest) throws EntityNotPersistedException;
    List<BookEntity> getByIds(final Set<Long> ids);
}
