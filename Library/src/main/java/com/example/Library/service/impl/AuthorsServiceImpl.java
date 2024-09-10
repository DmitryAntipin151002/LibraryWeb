package com.example.Library.service.impl;

import com.example.Library.model.Authors;
import com.example.Library.repository.AuthorsRepository;
import com.example.Library.service.AuthorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorsServiceImpl implements AuthorsService {
    private final AuthorsRepository authorsRepository;

    @Override
    public void deleteAuthor(String firstName, String lastName) {
        Optional<Authors> author = authorsRepository.findByFirstNameAndLastName(firstName, lastName);
        author.ifPresent(authorsRepository::delete);
    }

  @Override
    public Authors updateAuthor(String oldFirstName, String oldLastName, String newFirstName, String newLastName) {
        Optional<Authors> author = authorsRepository.findByFirstNameAndLastName(oldFirstName, oldLastName);
        if (author.isPresent()) {
            Authors updatedAuthor = author.get();
            updatedAuthor.setFirstName(newFirstName);
            updatedAuthor.setLastName(newLastName);
            return authorsRepository.save(updatedAuthor);
        }
        return null;
    }
}
