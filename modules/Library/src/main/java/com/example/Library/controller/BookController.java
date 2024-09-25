package com.example.Library.controller;

import com.example.Library.dto.BookDTO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.example.Library.service.BookService;

@Data
@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.findAllBooks());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Integer id) {
        return ResponseEntity.ok(bookService.findBookById(id));
    }

    @GetMapping("/get/{title}")
    public ResponseEntity<BookDTO> findBookByTitle(@PathVariable String title) {
        return ResponseEntity.ok(bookService.findBookByTitle(title));
    }

    @PostMapping("/add")
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO) {
        return new ResponseEntity<>(bookService.addBook(bookDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Integer id, @RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.updateBookById(id, bookDTO));
    }

    @PutMapping("/update/{title}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable String title, @RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.updateBookByTitle(title, bookDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}

