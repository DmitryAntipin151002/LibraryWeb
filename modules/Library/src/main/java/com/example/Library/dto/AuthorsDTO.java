package com.example.Library.dto;

import lombok.Data;

import java.util.List;

@Data
public class AuthorsDTO {
    public Integer authorId;
   public String firstName;
   public String lastName;
    private List<BookDTO> books;
}

