package com.example.provider.api.exception;

public class BookIsNotFreeException extends RuntimeException {
    private long id;
    public BookIsNotFreeException(long id) {
        super("Book with id " + id + " is taken by some user now!");
    }
}
