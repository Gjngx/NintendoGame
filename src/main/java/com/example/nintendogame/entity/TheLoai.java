package com.example.nintendogame.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "theLoai")
public class TheLoai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "theloai", length = 50, nullable = false)
    private String theloai;
    @OneToMany(mappedBy = "theLoai", cascade = CascadeType.ALL)
    private List<SanPham> sanPhams;
}
