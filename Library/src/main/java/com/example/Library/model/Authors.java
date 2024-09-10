package com.example.Library.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "authors", schema = "public")
@NoArgsConstructor
@Data
public class Authors {
    @Id
    @Column(name = "author_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    // Связь с таблицей books (OneToMany)
    @OneToMany(mappedBy = "authors")
    private List<Book> books;
}

