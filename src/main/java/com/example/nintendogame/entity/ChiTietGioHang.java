package com.example.nintendogame.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "chiTietGioHang")
public class ChiTietGioHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "soluong", nullable = false)
    private Long soluong;
    @Column(name = "tonggia", nullable = false)
    private double tonggia;
    @ManyToOne
    @JoinColumn(name = "giohang_id")
    private GioHang gioHang;
    @ManyToOne
    @JoinColumn(name = "sanpham_id")
    private SanPham sanPham;
}
