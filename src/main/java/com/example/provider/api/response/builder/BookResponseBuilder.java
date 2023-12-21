package com.example.provider.api.response.builder;

import com.example.provider.api.BookProviderEntity;
import com.example.provider.api.response.MultipleEntityResponse;
import com.example.provider.api.response.SingleEntityResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookResponseBuilder {
    public ResponseEntity<MultipleEntityResponse> buildMultipleEntityResponse(List<? extends BookProviderEntity> response) {
        return ResponseEntity.ok(MultipleEntityResponse.builder().response(response).build());
    }

    public ResponseEntity<SingleEntityResponse> buildSingleEntityResponse(BookProviderEntity response) {
        return ResponseEntity.ok(SingleEntityResponse.builder().response(response).build());
    }
}
