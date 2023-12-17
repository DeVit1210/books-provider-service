package com.example.provider.api.command.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {
    @Id
    private String id;
    private String isbn;
    private String name;
    @Enumerated(EnumType.STRING)
    private BookGenre genre;
    private String description;
    private String authorName;
}
