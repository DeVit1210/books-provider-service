package com.example.provider.api.controller;

import com.example.provider.api.request.BookDetailsRequest;
import com.example.provider.api.response.MultipleEntityResponse;
import com.example.provider.api.response.SingleEntityResponse;
import com.example.provider.api.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<MultipleEntityResponse> findAll() {
        return bookService.findAll();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<SingleEntityResponse> findById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<SingleEntityResponse> findByIsbn(@PathVariable String isbn) {
        return bookService.findByIsbn(isbn);
    }

    @GetMapping("/free")
    public ResponseEntity<MultipleEntityResponse> findAllFreeBooks() {
        return bookService.findAllFreeBooks();
    }

    @PostMapping
    public ResponseEntity<SingleEntityResponse> addBook(@RequestBody BookDetailsRequest request) {
        return bookService.addBook(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SingleEntityResponse> editBook(@PathVariable long id,
                                                         @RequestBody BookDetailsRequest request) {
        return bookService.editBook(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id) {
        bookService.deleteBook(id);
    }
}
