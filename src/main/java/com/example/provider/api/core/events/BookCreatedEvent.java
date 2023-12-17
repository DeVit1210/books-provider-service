package com.example.provider.api.core.events;

import com.example.provider.api.command.model.BookGenre;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookCreatedEvent {
    private String id;
    private String isbn;
    private String name;
    private BookGenre genre;
    private String description;
    private String authorName;
}
