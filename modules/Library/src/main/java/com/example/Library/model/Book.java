package com.example.Library.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "books", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   public Integer bookId;

    @Column(name = "isbn")
   public String isbn;

    @Column(name = "title")
   public String title;

    @Column(name = "genre")
   public String genre;

    @Column(name = "description")
   public String description;

    @JsonIgnore
    @Column(name = "author_id")
   public Integer authorId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "author_id", referencedColumnName = "author_id", insertable = false, updatable = false)
   public Authors authors;

    // Геттеры для автора
    public String getAuthorFirstName() {
        return authors != null ? authors.getFirstName() : "Неизвестно";
    }

    public String getAuthorLastName() {
        return authors != null ? authors.getLastName() : "Неизвестно";
    }
}







