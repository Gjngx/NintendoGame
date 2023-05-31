package com.example.nintendogame.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "roleAdmin")
public class RoleAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "rolename", length = 50, nullable = false)
    private String rolename;
    @OneToMany(mappedBy = "roleAdmin", cascade = CascadeType.ALL)
    private List<Admin> admins;
}
