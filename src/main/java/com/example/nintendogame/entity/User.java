package com.example.nintendogame.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", length = 50, nullable = false, unique = true)
    private String username;
    @Column(name = "password", length = 250, nullable = false)
    private String password;
    @Column(name = "email", length = 50, nullable = false)
    private String email;
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Column(name =" dia chi", length = 50)
    private String diachi;
    @Column(name = "sdt", length = 50, nullable = false)
    private String sdt;
    @Column(name = "gioitinh", length = 50, nullable = false)
    private String gioitinh;
    @Column(name = "ngaysinh", nullable = false)
    private Date ngaysinh;
    @Column(name = "trangthai", nullable = false)
    private boolean trangthai;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleUser roleUser;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<HoaDon> hoaDons;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<GioHang> gioHangs;
}
