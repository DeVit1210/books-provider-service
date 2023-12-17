package com.example.provider.api.query.controller;

import com.example.provider.api.core.dto.BookDto;
import com.example.provider.api.query.service.BookQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookQueryController {
    private final BookQueryService bookQueryService;
    @GetMapping
    public Mono<List<BookDto>> getAllBooks() {
        return bookQueryService.getAllBooks();
    }

    @GetMapping("id/{id}")
    public Mono<BookDto> getBookById(@PathVariable String id) {
        return bookQueryService.getBookById(id);
    }

    @GetMapping("isbn/{isbn}")
    public Mono<BookDto> getBookByIsbn(@PathVariable String isbn) {
        return bookQueryService.getBookByIsbn(isbn);
    }
}
