package com.example.provider.api.core.commands;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BookDeletedCommand {
    private String bookId;
}
