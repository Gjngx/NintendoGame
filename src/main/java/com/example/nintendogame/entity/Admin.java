package com.example.nintendogame.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", length = 50, nullable = false, unique = true)
    private String username;
    @Column(name = "password", length = 250, nullable = false)
    private String password;
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Column(name = "email", length = 50, nullable = false)
    private String email;
    @Column(name = "sdt", length = 13, nullable = false)
    private String sdt;
    @Column(name = "trangthai", nullable = false)
    private boolean trangthai;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleAdmin roleAdmin;
}
