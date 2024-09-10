package com.example.Library_Service.model;

import com.example.Library.model.Book;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "book_tracking", schema = "public")
@NoArgsConstructor
@Data
public class BookTracking {
    @Id
    @Column(name = "tracking_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer trackingId;

    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "borrowed_at")
    private LocalDateTime borrowedAt;

    @Column(name = "return_by")
    private LocalDateTime returnBy;

    // Связь с таблицей books (ManyToOne)
    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id", insertable = false, updatable = false)
    private Book book;
}
