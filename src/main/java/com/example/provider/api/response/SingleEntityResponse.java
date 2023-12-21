package com.example.provider.api.response;

import com.example.provider.api.BookProviderEntity;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SingleEntityResponse {
    private BookProviderEntity response;
}
