package com.example.Library.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "authors", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Data
    public class Authors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    public Integer authorId;

    @Column(name = "first_name")
   public String firstName;

    @Column(name = "last_name")
    public String lastName;

    @JsonManagedReference
    @OneToMany(mappedBy = "authors")
    private List<Book> books;
}

