package com.example.Library_Service.service;

import com.example.Library_Service.model.LibraryBook;

import java.time.LocalDateTime;

public interface LibraryService {
    public LibraryBook addBook(Integer bookId);

    public LibraryBook borrowBook(Integer bookId, LocalDateTime borrowTime, LocalDateTime returnTime);

    public LibraryBook getBookStatus(Integer bookId);
}
