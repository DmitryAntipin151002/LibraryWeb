package com.example.Library_Service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "book_tracking", schema = "public")
@NoArgsConstructor
@Data
public class LibraryBook {
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

}
