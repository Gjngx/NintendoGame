package com.example.nintendogame.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "chiTietHoaDon")
public class ChiTietHoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "soluong", nullable = false)
    private Long soluong;
    @Column(name = "tonggia", nullable = false)
    private double tonggia;
    @ManyToOne
    @JoinColumn(name = "hoadon_id")
    private HoaDon hoaDon;
    @ManyToOne
    @JoinColumn(name = "sanpham_id")
    private SanPham sanPham;
}
