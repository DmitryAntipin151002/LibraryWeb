package com.example.Library_Service.controller;

import com.example.Library_Service.dto.LibraryBookDTO;
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
    public ResponseEntity<LibraryBookDTO> addBook(@RequestParam Integer bookId) {
        LibraryBookDTO newLibraryBook = libraryService.addBook(bookId);
        return new ResponseEntity<>(newLibraryBook, HttpStatus.CREATED);
    }

    @PostMapping("/borrowBook")
    public ResponseEntity<LibraryBookDTO> borrowBook(@RequestBody LibraryBookDTO libraryBookDTO) {
        LibraryBookDTO updatedLibraryBook = libraryService.borrowBook(libraryBookDTO.getBookId(),
                libraryBookDTO.getBorrowedAt(), libraryBookDTO.getReturnBy());
        return new ResponseEntity<>(updatedLibraryBook, HttpStatus.OK);
    }


    @GetMapping("/status/{bookId}")
    public ResponseEntity<LibraryBookDTO> getBookStatus(@PathVariable Integer bookId) {
        LibraryBookDTO libraryBook = libraryService.getBookStatus(bookId);
        return new ResponseEntity<>(libraryBook, HttpStatus.OK);
    }
}


