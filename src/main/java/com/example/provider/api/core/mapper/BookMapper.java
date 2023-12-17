package com.example.provider.api.core.mapper;

import com.example.provider.api.command.model.Book;
import com.example.provider.api.core.commands.BookCreatedCommand;
import com.example.provider.api.core.commands.BookDeletedCommand;
import com.example.provider.api.core.commands.BookEditedCommand;
import com.example.provider.api.core.dto.BookDto;
import com.example.provider.api.core.events.BookCreatedEvent;
import com.example.provider.api.core.events.BookEditedEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(target = "genre", expression = "java(BookGenre.from(bookDto.getGenre()))")
    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    BookCreatedCommand toBookCreatedCommand(BookDto bookDto);

    @Mapping(target = "genre", expression = "java(BookGenre.from(bookDto.getGenre()))")
    BookEditedCommand toBookEditedCommand(BookDto bookDto, String bookToEditId);

    Book toBook(BookCreatedEvent event);

    Book toBook(BookEditedEvent event);

    @Mapping(target = "genre", source = "book.genre.description")
    BookDto toBookDto(Book book);

    List<BookDto> toBookDtoList(List<Book> bookList);
}
