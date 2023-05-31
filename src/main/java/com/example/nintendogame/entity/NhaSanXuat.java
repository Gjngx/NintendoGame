package com.example.nintendogame.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "nhaSanXuat")
public class NhaSanXuat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nsx", length = 50,nullable = false)
    private String nsx;
    @OneToMany(mappedBy = "nhaSanXuat", cascade = CascadeType.ALL)
    private List<SanPham> sanPhams;
}
