package com.example.Library.repository;

import com.example.Library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByIsbn(String isbn);
    List<Book> findByAuthorsFirstNameAndAuthorsLastName(String firstName, String lastName);
}
