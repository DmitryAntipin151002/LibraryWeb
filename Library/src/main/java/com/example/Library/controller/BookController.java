package com.example.Library.controller;

import com.example.Library.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Books/")
@RequiredArgsConstructor
public class BookController {
    private final BookService BookService;

    @GetMapping
    public List<Book> getAll() {
        return BookService.findAll();
    }

    @PostMapping
    public Book add(@RequestBody Book restaurant) {
        return Book.add(restaurant);
    }
}
