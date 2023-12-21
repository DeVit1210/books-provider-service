package com.example.provider.api.feign;

import jakarta.ws.rs.Path;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("LIBRARY-SERVICE")
public interface LibraryInterface {
    @PostMapping("/library")
    void addFreeBook(@RequestParam long bookId);

    @GetMapping("/library/allFree")
    List<Long> getFreeBooksIdList();

    @GetMapping("/library/isFree")
    boolean isBookFree(@RequestParam long bookId);

    @DeleteMapping("/library/{bookId}")
    void deleteBook(@PathVariable long bookId);
}
