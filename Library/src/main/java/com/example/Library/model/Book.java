package com.example.Library.model;


import com.example.Library_Service.model.BookTracking;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "books", schema = "public")
@NoArgsConstructor
@Data
public class Book {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "title")
    private String title;

    @Column(name = "genre")
    private String genre;

    @Column(name = "description")
    private String description;

    @Column(name = "author_id")
    private Integer authorId;

    // Связь с таблицей authors (ManyToOne)
    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "author_id", insertable = false, updatable = false)
    private Authors authors;

    // Связь с таблицей book_tracking (OneToMany)
    @OneToMany(mappedBy = "book")
    private List<BookTracking> bookTrackings;
}


