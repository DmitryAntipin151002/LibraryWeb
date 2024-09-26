package com.example.Library_Service.service.impl;

import com.example.Library_Service.dto.LibraryBookDTO;
import com.example.Library_Service.exception.BookAlreadyExistsException;
import com.example.Library_Service.exception.BookNotFoundException;
import com.example.Library_Service.mapper.LibraryBookMapper;
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

    @Autowired
    private LibraryBookMapper libraryBookMapper;

    @Override
    public LibraryBookDTO addBook(Integer bookId) {
        // Проверка на корректность ввода
        if (bookId == null || bookId <= 0) {
            throw new IllegalArgumentException("ID книги должен быть положительным числом.");
        }

        // Проверка на существование книги
        if (libraryBookRepository.findByBookId(bookId).isPresent()) {
            throw new BookAlreadyExistsException("Книга с ID " + bookId + " уже существует.");
        }

        LibraryBook libraryBook = new LibraryBook();
        libraryBook.setBookId(bookId);
        libraryBook.setBorrowedAt(null);  // Книга доступна, не взята
        libraryBook.setReturnBy(null);     // Нет времени возврата, так как не взята

        LibraryBook savedBook = libraryBookRepository.save(libraryBook);
        return libraryBookMapper.toDTO(savedBook);
    }


    @Override
    public LibraryBookDTO borrowBook(Integer bookId, LocalDateTime borrowTime, LocalDateTime returnTime) {
        // Проверка на корректность времени
        if (borrowTime == null) {
            throw new IllegalArgumentException("Время заимствования не может быть null.");
        }
        if (returnTime == null) {
            throw new IllegalArgumentException("Время возврата не может быть null.");
        }
        if (returnTime.isBefore(borrowTime)) {
            throw new IllegalArgumentException("Время возврата должно быть позже времени заимствования.");
        }

        LibraryBook libraryBook = libraryBookRepository.findByBookId(bookId)
                .orElseThrow(() -> new BookNotFoundException("Книга не найдена"));

        // Проверка на то, была ли книга уже взята
        if (libraryBook.getBorrowedAt() != null) {
            throw new RuntimeException("Книга уже была взята.");
        }

        libraryBook.setBorrowedAt(borrowTime);
        libraryBook.setReturnBy(returnTime);

        LibraryBook updatedBook = libraryBookRepository.save(libraryBook);
        return libraryBookMapper.toDTO(updatedBook);
    }



    @Override
    public LibraryBookDTO getBookStatus(Integer bookId) {
        LibraryBook libraryBook = libraryBookRepository.findByBookId(bookId)
                .orElseThrow(() -> new BookNotFoundException("Книга не найдена"));

        return libraryBookMapper.toDTO(libraryBook);
    }
}



