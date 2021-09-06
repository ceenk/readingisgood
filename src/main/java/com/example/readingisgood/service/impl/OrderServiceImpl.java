package com.example.readingisgood.service.impl;

import com.example.readingisgood.entity.BookEntity;
import com.example.readingisgood.entity.OrderContentEntity;
import com.example.readingisgood.entity.OrderEntity;
import com.example.readingisgood.exception.CustomerNotFoundException;
import com.example.readingisgood.exception.EntityNotPersistedException;
import com.example.readingisgood.exception.InsufficientStockCountException;
import com.example.readingisgood.exception.OrderNotFoundException;
import com.example.readingisgood.model.BookOrderModel;
import com.example.readingisgood.model.MonthlyStatistics;
import com.example.readingisgood.model.request.OrderRequest;
import com.example.readingisgood.repository.BookRepository;
import com.example.readingisgood.repository.OrderRepository;
import com.example.readingisgood.service.BookService;
import com.example.readingisgood.service.CustomerService;
import com.example.readingisgood.service.OrderContentService;
import com.example.readingisgood.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CustomerService customerService;
    private final OrderRepository orderRepository;
    private final BookService bookService;
    private final BookRepository bookRepository;
    private final OrderContentService orderContentService;

    @Override
    public List<OrderEntity> listAllOrders(final String username) throws CustomerNotFoundException {
        final var userEntity = customerService.getByUsername(username);
        final var orderEntities = orderRepository.findByUsername(userEntity.getUsername());

        return orderEntities;
    }

    @Override
    public OrderEntity createNewOrder(String username, OrderRequest orderRequest)
            throws InsufficientStockCountException, EntityNotPersistedException {

        final var bookCountMap = orderRequest.getBookOrderModels().stream()
                .collect(Collectors.toMap(BookOrderModel::getBookId, BookOrderModel::getCount));

        final var books = bookService.getByIds(bookCountMap.keySet());

        checkStock(books, bookCountMap);

        final var totalPrice = calculateTotalPrice(books, bookCountMap);
        final var totalBookCount = bookCountMap.values()
                .stream()
                .reduce(0, Integer::sum);

        var orderEntity = OrderEntity.builder()
                .username(username)
                .orderDate(LocalDate.now())
                .totalPrice(totalPrice)
                .totalBookCount(totalBookCount)
                .build();

        orderEntity = orderRepository.saveAndFlush(orderEntity);

        updateStockAfterOrder(books, bookCountMap, orderEntity.getId());

        return orderEntity;
    }

    @Override
    public OrderEntity getById(final Long orderId) throws OrderNotFoundException {
        final var order = orderRepository.findById(orderId);

        if (order.isEmpty()) {
            throw new OrderNotFoundException(
                    String.format("Cannot find order with id: %s", orderId)
            );
        }

        return order.get();
    }

    @Override
    public List<OrderEntity> getOrdersBetween(final String username, final String startDate, final String endDate) {
        final var startDateLocal = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        final var endDateLocal = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return orderRepository.findByUsernameAndOrderDateBetween(username, startDateLocal, endDateLocal);
    }

    @Override
    public Map<Month, MonthlyStatistics> listAllOrdersMonthly(String username) throws CustomerNotFoundException {
        final var allOrders = listAllOrders(username);
        final Map<Month, MonthlyStatistics> statisticsMap = new HashMap<>();

        final var months = allOrders.stream()
                .map(OrderEntity::getOrderDate)
                .map(LocalDate::getMonth)
                .collect(Collectors.toSet());


        return null;
    }

    private void checkStock(final List<BookEntity> books,
                            final Map<Long, Integer> bookCountMap) throws InsufficientStockCountException {

        for (BookEntity book : books) {
            final var orderCount = bookCountMap.get(book.getId());
            if (book.getStock().compareTo(orderCount) < 0) {
                throw new InsufficientStockCountException(
                        String.format("Stock not sufficient for book id: %s", book.getId())
                );
            }
        }
    }

    private BigDecimal calculateTotalPrice(final List<BookEntity> books,
                                           final Map<Long, Integer> bookCountMap) {

        final var totalPrice = books.stream()
                .map(book -> book.getPrice().multiply(new BigDecimal(bookCountMap.get(book.getId()))))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalPrice;
    }

    private void updateStockAfterOrder(final List<BookEntity> books,
                                       final Map<Long, Integer> bookCountMap,
                                       final Long orderId) throws EntityNotPersistedException {

        for (BookEntity book : books) {
            final Integer stock = book.getStock() - bookCountMap.get(book.getId());

            book.setStock(stock);

            final var bookEntity = bookRepository.saveAndFlush(book);

            if (bookEntity == null) {
                throw new EntityNotPersistedException(
                        String.format("Could not update stock of book with id %s", book.getId())
                );
            }

            saveOrderContent(bookEntity, bookCountMap.get(book.getId()), orderId);
        }
    }

    private void saveOrderContent(final BookEntity bookEntity,
                                  final Integer count,
                                  final Long orderId) throws EntityNotPersistedException {

        final var totalPrice = bookEntity.getPrice().multiply(new BigDecimal(count));

        final var orderContentEntity = OrderContentEntity.builder()
                .orderId(orderId)
                .bookId(bookEntity.getId())
                .count(count)
                .totalPrice(totalPrice)
                .build();

        final var orderContent = orderContentService.save(orderContentEntity);

        if (orderContent == null) {
            throw new EntityNotPersistedException(
                    String.format("Could not save order content with order id %s", orderId)
            );
        }
    }
}
