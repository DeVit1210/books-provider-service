package com.example.provider.api.core.commands;

import com.example.provider.api.command.model.BookGenre;
import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Getter
public class BookCreatedCommand {
    @TargetAggregateIdentifier
    private String id;
    private String isbn;
    private String name;
    private BookGenre genre;
    private String description;
    private String authorName;
}
