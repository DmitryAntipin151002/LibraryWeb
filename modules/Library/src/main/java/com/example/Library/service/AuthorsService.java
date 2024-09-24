package com.example.Library.service;

import com.example.Library.model.Authors;



public interface AuthorsService {

 void deleteAuthor(String firstName, String lastName);
 Authors updateAuthor(String oldFirstName, String oldLastName, String newFirstName, String newLastName);
}
