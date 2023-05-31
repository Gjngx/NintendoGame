package com.example.nintendogame.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "roleUser")
public class RoleUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "rolename", length = 50, nullable = false)
    private String rolename;
    @OneToMany(mappedBy = "roleUser", cascade = CascadeType.ALL)
    private List<User> users;
}
