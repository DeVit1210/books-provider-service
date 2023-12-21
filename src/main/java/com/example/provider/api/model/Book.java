package com.example.provider.api.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String isbn;
    private String name;
    @Enumerated(EnumType.STRING)
    private BookGenre genre;
    private String description;
    private String authorName;
}
