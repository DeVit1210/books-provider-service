package com.example.provider.api.dto;

import com.example.provider.api.BookProviderEntity;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BookDto implements BookProviderEntity {
    private Long id;
    private String isbn;
    private String name;
    private String genre;
    private String description;
    private String authorName;
}
