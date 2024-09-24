package com.example.Library.service.impl;

import com.example.Library.model.Book;
import java.util.List;
import com.example.Library.repository.BookRepository;
import com.example.Library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book findBookById(Integer id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        return optionalBook.orElse(null); // Возвращает null, если книга не найдена
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
        return optionalBook.orElse(null); // Возвращает null, если книга не найдена
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book); // Сохранение новой книги
    }

    @Override
    public Book updateBook(Integer id, Book book) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();
            // Обновляем поля существующей книги
            existingBook.setIsbn(book.getIsbn());
            existingBook.setTitle(book.getTitle());
            existingBook.setGenre(book.getGenre());
            existingBook.setDescription(book.getDescription());
            existingBook.setAuthorId(book.getAuthorId());
            return bookRepository.save(existingBook); // Сохранение изменений
        } else {
            return null; // Если книга не найдена, можно вернуть null или выбросить исключение
        }
    }

    @Override
    public List<Book> findBooksByAuthor(String firstName, String lastName) {
        return bookRepository.findByAuthorsFirstNameAndAuthorsLastName(firstName, lastName);
    }

    @Override
    public void deleteBook(Integer id) {
        bookRepository.deleteById(id); // Удаление книги по ID
    }
}

