package com.wrapy.blogpost.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "api_data"
)
public class ApiData {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(name = "url", nullable = false)
    private String url;
    @Column(name = "status", nullable = false)
    private int status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long executionTime;
}
