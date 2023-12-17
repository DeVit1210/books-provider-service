package com.example.provider.api.command.handler;

import com.example.provider.api.command.model.Book;
import com.example.provider.api.core.events.BookCreatedEvent;
import com.example.provider.api.core.events.BookDeletedEvent;
import com.example.provider.api.core.events.BookEditedEvent;
import com.example.provider.api.core.mapper.BookMapper;
import com.example.provider.api.core.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookEventHandler {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @EventHandler
    public void on(BookCreatedEvent event) {
        Book book = bookMapper.toBook(event);
        bookRepository.save(book);
    }

    @EventHandler
    public void on(BookEditedEvent event) {
        if(!bookRepository.existsById(event.getId())) {
            throw new IllegalArgumentException("book with id " + event.getId() + "was not found!");
        }
        Book book = bookMapper.toBook(event);
        bookRepository.save(book);
    }

    @EventHandler
    public void on(BookDeletedEvent event) {
        // TODO: check if it's not taken
        bookRepository.deleteById(event.getBookId());
    }
}
