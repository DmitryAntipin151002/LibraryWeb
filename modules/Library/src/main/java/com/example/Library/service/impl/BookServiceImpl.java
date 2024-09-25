package com.example.Library.service.impl;

import com.example.Library.dto.BookDTO;
import com.example.Library.exception.AuthorNotFoundException;
import com.example.Library.exception.BookNotFoundException;
import com.example.Library.exception.DuplicateIsbnException;
import com.example.Library.exception.DuplicateTitleException;
import com.example.Library.exception.DuplicateBookIdException;
import com.example.Library.mapper.BookMapper;
import com.example.Library.model.Book;
import com.example.Library.model.Authors;
import com.example.Library.repository.AuthorsRepository;
import com.example.Library.repository.BookRepository;
import com.example.Library.service.BookService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final LibraryServiceClientImpl libraryServiceClient;
    private final AuthorsRepository authorsRepository;


    @Override
    public List<BookDTO> findAllBooks() {
        return bookMapper.toDTOList(bookRepository.findAll());
    }

    @Override
    public BookDTO findBookById(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + id));
        return bookMapper.toDTO(book);
    }

    @Override
    public BookDTO findBookByTitle(String title) {
        Book book = bookRepository.findByTitle(title)
                .orElseThrow(() -> new BookNotFoundException("Book not found with title: " + title));
        return bookMapper.toDTO(book);
    }

    @Override
    public BookDTO addBook(BookDTO bookDTO) {
        if (bookDTO.getBookId() != null && bookRepository.existsById(bookDTO.getBookId())) {
            throw new DuplicateBookIdException("Book with this ID already exists");
        }
        if (bookRepository.existsByIsbn(bookDTO.getIsbn())) {
            throw new DuplicateIsbnException("ISBN already exists");
        }
        if (bookRepository.existsByTitle(bookDTO.getTitle())) {
            throw new DuplicateTitleException("Book with this title already exists");
        }
        // Преобразовать DTO в сущность
        Book book = bookMapper.toEntity(bookDTO);
        // Предполагается, что Book имеет поле author, которое вы можете установить
        if (bookDTO.getAuthorId() != null) {
            Authors author = authorsRepository.findById(bookDTO.getAuthorId())
                    .orElseThrow(() -> new AuthorNotFoundException("Author not found with ID: " + bookDTO.getAuthorId()));

            book.setAuthor(author); // Устанавливаем автора в книге
        }

        // Сохранить книгу
        Book savedBook = bookRepository.save(book);
        // Уведомить библиотечный сервис
        libraryServiceClient.notifyLibraryService(savedBook.getBookId());
        return bookMapper.toDTO(savedBook);
    }


    @Override
    public BookDTO updateBookById(Integer id, BookDTO bookDTO) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + id));

        existingBook.setIsbn(bookDTO.getIsbn());
        existingBook.setTitle(bookDTO.getTitle());
        existingBook.setGenre(bookDTO.getGenre());
        existingBook.setDescription(bookDTO.getDescription());

        Book updatedBook = bookRepository.save(existingBook);
        return bookMapper.toDTO(updatedBook);
    }

    @Override
    public BookDTO updateBookByTitle(String title, BookDTO bookDTO) {
        Book existingBook = bookRepository.findByTitle(title)
                .orElseThrow(() -> new BookNotFoundException("Book not found with title: " + title));

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


}
