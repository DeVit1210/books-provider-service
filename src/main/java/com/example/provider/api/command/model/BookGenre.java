package com.example.provider.api.command.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum BookGenre {
    SOME_GENRE("some_genre"), OTHER("other");
    private final String description;

    public static BookGenre from(String genre) {
        return Arrays.stream(values())
                .filter(bookGenre -> bookGenre.description.equals(genre))
                .findAny()
                .orElse(BookGenre.OTHER);
    }
}
