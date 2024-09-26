package com.example.Library_Service.service;

import com.example.Library_Service.dto.LibraryBookDTO;
import com.example.Library_Service.model.LibraryBook;

import java.time.LocalDateTime;

public interface LibraryService {
    public  LibraryBookDTO addBook(Integer bookId);

    public  LibraryBookDTO borrowBook(Integer bookId, LocalDateTime borrowTime, LocalDateTime returnTime);

    public LibraryBookDTO getBookStatus(Integer bookId);
}
