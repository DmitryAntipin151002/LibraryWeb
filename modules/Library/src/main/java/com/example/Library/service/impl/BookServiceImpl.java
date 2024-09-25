package com.example.Library.service.impl;

import com.example.Library.dto.BookDTO;
import com.example.Library.exception.BookNotFoundException;
import com.example.Library.exception.DuplicateIsbnException;
import com.example.Library.exception.DuplicateTitleException;
import com.example.Library.mapper.BookMapper;
import com.example.Library.model.Book;
import com.example.Library.repository.BookRepository;
import com.example.Library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final LibraryServiceClientImpl libraryServiceClient;

    @Override
    public List<BookDTO> findAllBooks() {
        return bookMapper.toDTOList(bookRepository.findAll());
    }

    @Override
    public BookDTO findBookById(Integer id) {
        // Поиск книги по ID
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + id));
        return bookMapper.toDTO(book);
    }
    @Override
    public BookDTO findBookByTitle(String title) {
        // Поиск книги по названию
        Book book = bookRepository.findByTitle(title)
                .orElseThrow(() -> new BookNotFoundException("Book not found with title: " + title));
        return bookMapper.toDTO(book);
    }

    @Override
    public BookDTO addBook(BookDTO bookDTO) {
        // Проверка на существование ISBN
        if (bookRepository.existsByIsbn(bookDTO.getIsbn())) {
            throw new DuplicateIsbnException("ISBN already exists");
        }

        // Проверка на существование книги с таким же названием
        if (bookRepository.existsByTitle(bookDTO.getTitle())) {
            throw new DuplicateTitleException("Book with this title already exists");
        }

        Book book = bookMapper.toEntity(bookDTO);
        Book savedBook = bookRepository.save(book);

        // Уведомление библиотечного сервиса
        libraryServiceClient.notifyLibraryService(savedBook.getBookId());
        return bookMapper.toDTO(savedBook);
    }

    @Override
    public BookDTO updateBookById(Integer id, BookDTO bookDTO) {
        // Проверка на существование книги перед обновлением по ID
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + id));

        // Обновление данных книги
        existingBook.setIsbn(bookDTO.getIsbn());
        existingBook.setTitle(bookDTO.getTitle());
        existingBook.setGenre(bookDTO.getGenre());
        existingBook.setDescription(bookDTO.getDescription());

        Book updatedBook = bookRepository.save(existingBook);
        return bookMapper.toDTO(updatedBook);
    }

    @Override
    public BookDTO updateBookByTitle(String title, BookDTO bookDTO) {
        // Проверка на существование книги перед обновлением по названию
        Book existingBook = bookRepository.findByTitle(title)
                .orElseThrow(() -> new BookNotFoundException("Book not found with title: " + title));

        // Обновление данных книги
        existingBook.setIsbn(bookDTO.getIsbn());
        existingBook.setTitle(bookDTO.getTitle());
        existingBook.setGenre(bookDTO.getGenre());
        existingBook.setDescription(bookDTO.getDescription());

        Book updatedBook = bookRepository.save(existingBook);
        return bookMapper.toDTO(updatedBook);
    }

    @Override
    public void deleteBook(Integer id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book not found with ID: " + id);
        }
        bookRepository.deleteById(id);
    }

    public void deleteBookByTitle(String title) {
        Book book = bookRepository.findByTitle(title)
                .orElseThrow(() -> new BookNotFoundException("Book not found with title: " + title));
        bookRepository.delete(book);
    }
}
