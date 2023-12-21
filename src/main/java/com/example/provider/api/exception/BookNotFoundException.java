package com.example.provider.api.exception;

public class BookNotFoundException extends RuntimeException {
    private long id;
    public BookNotFoundException(long id) {
        super("Book with id " + id + " was not found!");
    }

    public BookNotFoundException(String message) {
        super(message);
    }
}
