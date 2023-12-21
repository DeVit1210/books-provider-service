package com.example.provider.api.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookDetailsRequest {
    private String isbn;
    private String name;
    private String genre;
    private String description;
    private String authorName;
}
