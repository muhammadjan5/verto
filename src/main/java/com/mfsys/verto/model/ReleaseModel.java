package com.mfsys.verto.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "releases")
@Getter
@Setter
public class ReleaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key

    @Column(nullable = false, length = 50)
    private String branch;

    @Column(nullable = false)
    private Integer build;

    @Column(nullable = false, length = 100)
    private String client;

    @Column(name = "commit_message", length = 500)
    private String commitMessage;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false, length = 50)
    private String environment;

    @Column(nullable = false, length = 20)
    private String version;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
