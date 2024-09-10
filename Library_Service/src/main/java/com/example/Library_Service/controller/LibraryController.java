package com.example.Library_Service.controller;

import com.example.Library_Service.model.LibraryBook;
import com.example.Library_Service.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @PostMapping("/addBook")
    public ResponseEntity<LibraryBook> addBook(@RequestParam Integer bookId) {
        LibraryBook newLibraryBook = libraryService.addBook(bookId);
        return new ResponseEntity<>(newLibraryBook, HttpStatus.CREATED);
    }

    @PostMapping("/borrowBook")
    public ResponseEntity<LibraryBook> borrowBook(
            @RequestParam Integer bookId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime borrowTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime returnTime) {

        LibraryBook updatedLibraryBook = libraryService.borrowBook(bookId, borrowTime, returnTime);
        return new ResponseEntity<>(updatedLibraryBook, HttpStatus.OK);
    }

    @GetMapping("/status/{bookId}")
    public ResponseEntity<LibraryBook> getBookStatus(@PathVariable Integer bookId) {
        LibraryBook libraryBook = libraryService.getBookStatus(bookId);
        return new ResponseEntity<>(libraryBook, HttpStatus.OK);
    }
}

