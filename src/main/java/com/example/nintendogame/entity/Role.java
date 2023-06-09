package com.example.nintendogame.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(max = 50, message = "Name must be less than 50 character")
    @NotBlank(message = "Name is required")
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Size(max = 250, message = "Name must be less than 250 character")
    @Column(name = "description", length = 250)
    private String description;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();
}
