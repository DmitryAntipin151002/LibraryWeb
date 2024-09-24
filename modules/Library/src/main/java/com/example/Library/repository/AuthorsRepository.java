package com.example.Library.repository;

import com.example.Library.model.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AuthorsRepository extends JpaRepository<Authors, Integer> {
    Optional<Authors> findByFirstNameAndLastName(String firstName, String lastName);
}
