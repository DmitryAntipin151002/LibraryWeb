package com.example.Library.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "books", schema = "public")
@NoArgsConstructor
@Data
public class Book {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer bookId;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "title")
    private String title;

    @Column(name = "genre")
    private String genre;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @Column(name = "author_id")
    private Integer authorId;

    // Связь с таблицей authors (ManyToOne)
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "author_id", referencedColumnName = "author_id", insertable = false, updatable = false)
    private Authors authors;

    public String getAuthorFirstName() {
       if (authors == null) {
           return "Неизвестно";
       }
       else return authors.getFirstName();
    }

    public String getAuthorLastName() {
       if (authors == null) {
           return "Неизвестно";
       }
       else return authors.getLastName();
    }


}



