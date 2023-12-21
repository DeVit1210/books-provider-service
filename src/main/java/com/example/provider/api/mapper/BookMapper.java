package com.example.provider.api.mapper;

import com.example.provider.api.dto.BookDto;
import com.example.provider.api.model.Book;
import com.example.provider.api.request.BookDetailsRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(target = "genre", source = "book.genre.description")
    BookDto toBookDto(Book book);

    @Mapping(target = "genre", expression = "java(BookGenre.from(request.getGenre()))")
    Book toBook(BookDetailsRequest request);
}
