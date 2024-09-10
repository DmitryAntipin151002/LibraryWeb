package com.example.Library.controller;

import com.example.Library.model.Authors;
import com.example.Library.service.AuthorsService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequestMapping("/api/authors")
public class AuthorsController {

    private final AuthorsService authorsService;

    @Autowired
    public AuthorsController(AuthorsService authorsService) {
        this.authorsService = authorsService;
    }

    @DeleteMapping
    public String deleteAuthor(@RequestParam String firstName, @RequestParam String lastName) {
        authorsService.deleteAuthor(firstName, lastName);
        return "Author " + firstName + " " + lastName + " has been deleted.";
    }

    @PutMapping
    public Authors updateAuthor(@RequestParam String oldFirstName, @RequestParam String oldLastName,
                                @RequestParam String newFirstName, @RequestParam String newLastName) {
        return authorsService.updateAuthor(oldFirstName, oldLastName, newFirstName, newLastName);
    }
}
