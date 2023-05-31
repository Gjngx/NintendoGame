package com.example.nintendogame.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "hoaDon")
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    @CreationTimestamp
    @Column(name = "ngaytao")
    private Date ngaytao;
    @Column(name =" diachinhanhang", length = 150, nullable = false)
    private String diachinhanhang;
    @Column(name = "trangthai", length = 50, nullable = false)
    private String trangthai;
    @Column(name = "ghichu", length = 350)
    private String ghichu;
    @Column(name = "tonggia", nullable = false)
    private double tonggia;
    @Column(name = "trangthaithanhtoan", nullable = false)
    private boolean trangthaithanhtoan;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne
    @JoinColumn(name = "thanhtoan_id")
    private ThanhToan thanhToan;
    @OneToMany(mappedBy = "hoaDon", cascade = CascadeType.ALL)
    private List<ChiTietHoaDon> chiTietHoaDons;
}
