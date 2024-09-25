package com.example.Library.service;

import com.example.Library.dto.BookDTO;
import com.example.Library.model.Book;
import java.util.List;


public interface BookService {
    public List<BookDTO> findAllBooks();
    public BookDTO findBookById(Integer id);
    public BookDTO addBook(BookDTO bookDTO);
    public BookDTO findBookByTitle(String title);
    public BookDTO updateBookByTitle(String title, BookDTO bookDTO);
    BookDTO updateBookById(Integer id, BookDTO bookDTO);
    public void deleteBook(Integer id);
}
