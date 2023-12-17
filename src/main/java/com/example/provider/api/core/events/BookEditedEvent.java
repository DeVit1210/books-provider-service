package com.example.provider.api.core.events;

import com.example.provider.api.command.model.BookGenre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookEditedEvent {
    private String id;
    private String isbn;
    private String name;
    private BookGenre genre;
    private String description;
    private String authorName;
}
