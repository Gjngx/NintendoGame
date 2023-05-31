package com.example.nintendogame.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "gioHang")
public class GioHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tonggia", nullable = false)
    private double tonggia;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "gioHang", cascade = CascadeType.ALL)
    private List<ChiTietGioHang> chiTietGioHangs;
}
