package com.example.provider.api.query.projector;

import com.example.provider.api.command.model.Book;
import com.example.provider.api.core.queries.GetAllBooksQuery;
import com.example.provider.api.core.queries.GetBookByIdQuery;
import com.example.provider.api.core.queries.GetBookByIsbnQuery;
import com.example.provider.api.core.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookProjector {
    private final BookRepository bookRepository;

    @QueryHandler
    public List<Book> on(GetAllBooksQuery query) {
        return bookRepository.findAll();
    }

    @QueryHandler
    public Book on(GetBookByIdQuery query) {
        return bookRepository.findById(query.getBookId())
                .orElseThrow(() -> new IllegalArgumentException("book with id " + query.getBookId() + " was not found!"));
    }

    @QueryHandler
    public Book on(GetBookByIsbnQuery query) {
        return bookRepository.findByIsbn(query.getIsbn())
                .orElseThrow(() -> new IllegalArgumentException("book with isbn " + query.getIsbn() + " was not found!"));
    }
}
