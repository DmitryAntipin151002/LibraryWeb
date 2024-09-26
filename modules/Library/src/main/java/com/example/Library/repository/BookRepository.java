package com.example.Library.repository;

import com.example.Library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import com.example.Library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {

    // Проверка существования книги по ISBN
    boolean existsByIsbn(String isbn);

    // Поиск книги по названию
    Optional<Book> findByTitle(String title);

    // Проверка существования книги по названию
    boolean existsByTitle(String title);
}
