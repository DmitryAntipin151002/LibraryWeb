package com.example.Library.service;

import com.example.Library.model.Authors;

import java.util.Optional;


public interface AuthorsService {

 void deleteAuthor(String firstName, String lastName);
 Authors updateAuthor(String oldFirstName, String oldLastName, String newFirstName, String newLastName);
 public Optional<Authors> findById(Integer authorId);
}
