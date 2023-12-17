package com.example.provider.api.command.aggregate;

import com.example.provider.api.command.model.BookGenre;
import com.example.provider.api.core.commands.BookCreatedCommand;
import com.example.provider.api.core.commands.BookDeletedCommand;
import com.example.provider.api.core.commands.BookEditedCommand;
import com.example.provider.api.core.events.BookCreatedEvent;
import com.example.provider.api.core.events.BookDeletedEvent;
import com.example.provider.api.core.events.BookEditedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@NoArgsConstructor
public class BookAggregate {
    @AggregateIdentifier
    private String id;
    private String isbn;
    private String name;
    private BookGenre genre;
    private String description;
    private String authorName;

    @CommandHandler
    public BookAggregate(BookCreatedCommand bookCreatedCommand) {
        BookCreatedEvent bookCreatedEvent = new BookCreatedEvent();
        BeanUtils.copyProperties(bookCreatedCommand, bookCreatedEvent);
        AggregateLifecycle.apply(bookCreatedEvent);
    }

    @CommandHandler
    public void handle(BookEditedCommand bookEditedCommand) {
        BookEditedEvent bookEditedEvent = new BookEditedEvent();
        BeanUtils.copyProperties(bookEditedCommand, bookEditedEvent);
        AggregateLifecycle.apply(bookEditedEvent);
    }

    @CommandHandler
    public void handle(BookDeletedCommand bookDeletedCommand) {
        AggregateLifecycle.apply(BookDeletedEvent.builder().bookId(bookDeletedCommand.getBookId()).build());
    }

    @EventSourcingHandler
    public void on(BookCreatedEvent event) {
        this.id = event.getId();
        this.isbn = event.getIsbn();
        this.name = event.getName();
        this.genre = event.getGenre();
        this.description = event.getDescription();
        this.authorName = event.getAuthorName();
    }

    @EventSourcingHandler
    public void on(BookEditedEvent event) {
        this.id = event.getId();
        this.isbn = event.getIsbn();
        this.name = event.getName();
        this.genre = event.getGenre();
        this.description = event.getDescription();
        this.authorName = event.getAuthorName();
    }
}
