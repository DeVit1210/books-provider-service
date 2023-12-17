package com.example.provider.api.command.controller;

import com.example.provider.api.command.service.BookProviderCommandService;
import com.example.provider.api.core.dto.BookDto;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookProviderCommandController {
    private final BookProviderCommandService bookProviderCommandService;

    @PostMapping
    public Mono<BookDto> addBook(@RequestBody BookDto bookDetails) {
        return bookProviderCommandService.addBook(bookDetails);
    }

    @PutMapping("/{bookId}")
    public Mono<BookDto> editBook(@PathVariable String bookId, @RequestBody BookDto bookDetails) {
        return bookProviderCommandService.editBook(bookDetails, bookId);
    }

    @DeleteMapping("/{bookId}")
    public Mono<Void> deleteBook(@PathVariable String bookId) {
        return bookProviderCommandService.deleteBook(bookId);
    }
}
