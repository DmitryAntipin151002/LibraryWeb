package com.example.Library.service;

import com.example.Library.model.Book;
import java.util.List;


public interface BookService {
    List<Book> findAllBooks();

    Book findBookById(Integer id);

    Book findBookByIsbn(String isbn);

    Book addBook(Book book);

    Book updateBook(Integer id, Book book);

    public List<Book> findBooksByAuthor(String firstName, String lastName);

    void deleteBook(Integer id);
}
