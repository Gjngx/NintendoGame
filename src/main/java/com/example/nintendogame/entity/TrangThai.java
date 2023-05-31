package com.example.nintendogame.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "trangThai")
public class TrangThai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "trangthai", length = 50, nullable = false)
    private String trangthai;
    @OneToMany(mappedBy = "trangThai", cascade = CascadeType.ALL)
    private List<SanPham> sanPhams;
}
