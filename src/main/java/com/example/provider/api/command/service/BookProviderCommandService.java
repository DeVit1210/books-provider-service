package com.example.provider.api.command.service;

import com.example.provider.api.command.model.Book;
import com.example.provider.api.core.commands.BookCreatedCommand;
import com.example.provider.api.core.commands.BookDeletedCommand;
import com.example.provider.api.core.commands.BookEditedCommand;
import com.example.provider.api.core.dto.BookDto;
import com.example.provider.api.core.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BookProviderCommandService {
    private final BookMapper bookMapper;
    private final ReactorCommandGateway commandGateway;
    public Mono<BookDto> addBook(BookDto bookDetails) {
        BookCreatedCommand command = bookMapper.toBookCreatedCommand(bookDetails);
        return commandGateway.send(command).map(result -> bookDetails);
    }

    public Mono<BookDto> editBook(BookDto bookDetails, String bookToEditId) {
        BookEditedCommand command = bookMapper.toBookEditedCommand(bookDetails, bookToEditId);
        return commandGateway.send(command).map(result -> bookDetails);
    }

    public Mono<Void> deleteBook(String bookId) {
        BookDeletedCommand command = BookDeletedCommand.builder().bookId(bookId).build();
        return commandGateway.send(command);
    }
}
