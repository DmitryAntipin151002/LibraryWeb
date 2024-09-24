package com.example.Library_Service.service.impl;

import com.example.Library_Service.model.LibraryBook;
import com.example.Library_Service.repository.LibraryBookRepository;
import com.example.Library_Service.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private LibraryBookRepository libraryBookRepository;

    public LibraryBook addBook(Integer bookId) {
        LibraryBook libraryBook = new LibraryBook();
        libraryBook.setBookId(bookId);
        libraryBook.setBorrowedAt(null);  // Книга доступна, не взята
        libraryBook.setReturnBy(null);  // Нет времени возврата, так как не взята

        return libraryBookRepository.save(libraryBook);
    }

    public LibraryBook borrowBook(Integer bookId, LocalDateTime borrowTime, LocalDateTime returnTime) {
        Optional<LibraryBook> optionalLibraryBook = libraryBookRepository.findByBookId(bookId);
        if (optionalLibraryBook.isPresent()) {
            LibraryBook libraryBook = optionalLibraryBook.get();
            libraryBook.setBorrowedAt(borrowTime);
            libraryBook.setReturnBy(returnTime);

            return libraryBookRepository.save(libraryBook);
        } else {
            throw new RuntimeException("Book not found");
        }
    }

    public LibraryBook getBookStatus(Integer bookId) {
        return libraryBookRepository.findByBookId(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }
}

