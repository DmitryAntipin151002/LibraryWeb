package com.example.Library_Service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LibraryBookDTO {
    private Integer bookId;
    private LocalDateTime borrowedAt;
    private LocalDateTime returnBy;
}

