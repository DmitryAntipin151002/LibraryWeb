package com.example.Library.controller;

import com.example.Library.model.Book;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.example.Library.service.BookService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Data
@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;
    @Autowired
    private final RestTemplate restTemplate;

    @Autowired
    public BookController(BookService bookService, RestTemplate restTemplate) {
        this.bookService = bookService;
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Integer id) {
        return bookService.findBookById(id);
    }

    @GetMapping("/author")
    public List<Book> getBooksByAuthor(@RequestParam String firstName, @RequestParam String lastName) {
        return bookService.findBooksByAuthor(firstName, lastName);
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book newBook = bookService.addBook(book);

        // Отправка запроса в LibraryService
        restTemplate.postForEntity("http://localhost:8081/libraryService/addBook?bookId=" + newBook.getBookId(), null, Void.class);

        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Integer id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
    }
}
