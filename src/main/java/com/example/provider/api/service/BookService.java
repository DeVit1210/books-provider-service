package com.example.provider.api.service;

import com.example.provider.api.dto.BookDto;
import com.example.provider.api.exception.BookIsNotFreeException;
import com.example.provider.api.exception.BookNotFoundException;
import com.example.provider.api.feign.LibraryInterface;
import com.example.provider.api.mapper.BookMapper;
import com.example.provider.api.model.Book;
import com.example.provider.api.repository.BookRepository;
import com.example.provider.api.request.BookDetailsRequest;
import com.example.provider.api.response.MultipleEntityResponse;
import com.example.provider.api.response.SingleEntityResponse;
import com.example.provider.api.response.builder.BookResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookResponseBuilder bookResponseBuilder;
    private final LibraryInterface libraryInterface;

    public ResponseEntity<MultipleEntityResponse> findAll() {
        List<BookDto> allBooks = bookRepository.findAll().stream().map(bookMapper::toBookDto).toList();
        return bookResponseBuilder.buildMultipleEntityResponse(allBooks);
    }

    public ResponseEntity<SingleEntityResponse> findById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        return bookResponseBuilder.buildSingleEntityResponse(bookMapper.toBookDto(book));
    }

    public ResponseEntity<SingleEntityResponse> findByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException("book with isbn " + isbn + " was not found!"));
        return bookResponseBuilder.buildSingleEntityResponse(bookMapper.toBookDto(book));
    }

    public ResponseEntity<SingleEntityResponse> addBook(BookDetailsRequest request) {
        Book book = bookMapper.toBook(request);
        Book savedBook = bookRepository.save(book);
        libraryInterface.addFreeBook(savedBook.getId());
        return bookResponseBuilder.buildSingleEntityResponse(bookMapper.toBookDto(savedBook));
    }

    public ResponseEntity<SingleEntityResponse> editBook(long id, BookDetailsRequest request) {
        if (bookRepository.existsById(id)) {
            Book book = bookMapper.toBook(request);
            Book savedBook = bookRepository.save(book.toBuilder().id(id).build());
            return bookResponseBuilder.buildSingleEntityResponse(bookMapper.toBookDto(savedBook));
        } else throw new BookIsNotFreeException(id);
    }

    public void deleteBook(long id) {
        Long bookId = bookRepository.findById(id)
                .map(Book::getId)
                .filter(libraryInterface::isBookFree)
                .orElseThrow(() -> new BookIsNotFreeException(id));
        libraryInterface.deleteBook(bookId);
        bookRepository.deleteById(bookId);
    }

    public ResponseEntity<MultipleEntityResponse> findAllFreeBooks() {
        List<Long> freeBooksIdList = libraryInterface.getFreeBooksIdList();
        List<BookDto> freeBookDtoList = bookRepository.findAllByIdIn(freeBooksIdList).stream()
                .map(bookMapper::toBookDto)
                .toList();
        return bookResponseBuilder.buildMultipleEntityResponse(freeBookDtoList);
    }
}
