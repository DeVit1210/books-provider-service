package com.example.provider.api.response;

import com.example.provider.api.BookProviderEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class MultipleEntityResponse {
    private List<? extends BookProviderEntity> response;
}
