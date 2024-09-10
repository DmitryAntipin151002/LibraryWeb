package com.example.Library_Service.repository;


import com.example.Library_Service.model.LibraryBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibraryBookRepository extends JpaRepository<LibraryBook, Integer> {
    Optional<LibraryBook> findByBookId(Integer bookId);
}