package com.example.nintendogame.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Entity
@Data
@Table(name = "thanhToan")
public class ThanhToan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "phuongthucthanhtoan", length = 50, nullable = false)
    private String phuongthucthanhtoan;
    @Column(name = "ngaythanhtoan", nullable = false)
    private Date ngaythanhtoan;
    @OneToOne(mappedBy = "thanhToan", cascade = CascadeType.ALL)
    private List<HoaDon> hoaDons;
}
