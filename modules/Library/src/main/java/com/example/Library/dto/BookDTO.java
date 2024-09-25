package com.example.Library.dto;

import lombok.Data;


@Data
public class BookDTO {
   public Integer bookId;          // Идентификатор книги
   public String isbn;             // ISBN книги
   public String title;            // Название книги
   public String genre;            // Жанр книги
   public String description;      // Описание книги
   public String firstName;  // Имя автора
   public String lastName;   // Фамилия автора

}

