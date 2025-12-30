package com.mfsys.verto.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String firstName;
    private String lastName;

    // Optional fields
    private String displayName;
    private String avatarUrl;
    private String jobTitle;
    private String location;
    private String bio;
    private String phoneNumber;
}
