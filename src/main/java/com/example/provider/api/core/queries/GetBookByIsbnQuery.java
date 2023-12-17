package com.example.provider.api.core.queries;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class GetBookByIsbnQuery {
    private String isbn;
}
