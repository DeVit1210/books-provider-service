package com.example.provider.api.core.commands;

import com.example.provider.api.command.model.BookGenre;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class BookEditedCommand {
    private String bookToEditId;
    private String isbn;
    private String name;
    private BookGenre genre;
    private String description;
    private String authorName;
}
