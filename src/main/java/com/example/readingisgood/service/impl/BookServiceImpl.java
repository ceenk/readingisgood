package com.example.readingisgood.service.impl;

import com.example.readingisgood.entity.BookEntity;
import com.example.readingisgood.exception.EntityNotPersistedException;
import com.example.readingisgood.model.request.BookRequest;
import com.example.readingisgood.model.request.StockUpdateRequest;
import com.example.readingisgood.model.response.BookResponse;
import com.example.readingisgood.repository.BookRepository;
import com.example.readingisgood.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public BookResponse save(BookRequest bookRequest) throws EntityNotPersistedException {
        var bookEntity = BookEntity.builder()
                .author(bookRequest.getAuthor())
                .title(bookRequest.getTitle())
                .price(bookRequest.getPrice())
                .stock(bookRequest.getStock())
                .build();

        bookEntity = bookRepository.saveAndFlush(bookEntity);

        if (bookEntity == null) {
            throw new EntityNotPersistedException(
                    String.format("Cannot persist book with title: %s", bookRequest.getTitle()));
        }

        final var response = BookResponse.builder()
                .title(bookRequest.getTitle())
                .message(String.format("Successfully inserted book with id: %s", bookEntity.getId()))
                .build();

        return response;
    }

    @Override
    public BookResponse updateStock(final StockUpdateRequest stockUpdateRequest) throws EntityNotPersistedException {
        try {
            bookRepository.updateStock(stockUpdateRequest.getId(), stockUpdateRequest.getStock());
        } catch (PersistenceException e) {
            throw new EntityNotPersistedException(e.getMessage());
        }

        final var response = BookResponse.builder()
                .message(String.format("Successfully updated stock count to %s book with id: %s",
                        stockUpdateRequest.getStock(),
                        stockUpdateRequest.getId()))
                .build();

        return response;
    }

    @Override
    public List<BookEntity> getByIds(Set<Long> ids) {
        final var books = bookRepository.findAllById(ids);

        return books;
    }
}
