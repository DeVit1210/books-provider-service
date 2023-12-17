package com.example.provider.api.query.service;

import com.example.provider.api.command.model.Book;
import com.example.provider.api.core.dto.BookDto;
import com.example.provider.api.core.mapper.BookMapper;
import com.example.provider.api.core.queries.GetAllBooksQuery;
import com.example.provider.api.core.queries.GetBookByIdQuery;
import com.example.provider.api.core.queries.GetBookByIsbnQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.extensions.reactor.queryhandling.gateway.ReactorQueryGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookQueryService {
    private final ReactorQueryGateway queryGateway;
    private final BookMapper bookMapper;

    public Mono<List<BookDto>> getAllBooks() {
        return queryGateway.query(new GetAllBooksQuery(), ResponseTypes.multipleInstancesOf(Book.class))
                .map(bookMapper::toBookDtoList);
    }

    public Mono<BookDto> getBookById(String bookId) {
        return queryAndMap(GetBookByIdQuery.builder().bookId(bookId).build());
    }

    public Mono<BookDto> getBookByIsbn(String isbn) {
        return queryAndMap(GetBookByIsbnQuery.builder().isbn(isbn).build());
    }

    private Mono<BookDto> queryAndMap(Object query) {
        return queryGateway.query(query, Book.class)
                .map(bookMapper::toBookDto);
    }
}
