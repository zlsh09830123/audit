package com.example.audit.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "change_log")
@Data
public class ChangeLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "changes")
    @ElementCollection
    private Map<String, Map<String, ChangeRecord>> changes = new HashMap<>();

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    // Getters and Setters
}

