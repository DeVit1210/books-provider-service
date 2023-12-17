package com.example.provider.api.core.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BookDto {
    private String isbn;
    private String name;
    private String genre;
    private String description;
    private String authorName;
}
