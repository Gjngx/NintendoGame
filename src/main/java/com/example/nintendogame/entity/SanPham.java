package com.example.nintendogame.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Entity
@Data
@Table(name = "sanPham")
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "sanpham", length = 50, nullable = false, unique = true)
    private String sanpham;
    @Column(name = "mota", length = 500, nullable = false)
    private String mota;
    @Column(name = "soluong", length = 50, nullable = false)
    private int soluong;
    @Column(name = "hinhanh", length = 150, nullable = false)
    private String hinhanh;
    @Column(name = "gia")
    private double gia;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    @CreationTimestamp
    @Column(name = "ngaydangsanpham")
    private Date ngaydangsanpham;
    @ManyToOne
    @JoinColumn(name = "theloai_id")
    private TheLoai theLoai;
    @ManyToOne
    @JoinColumn(name = "trangthai_id")
    private TrangThai trangThai;
    @ManyToOne
    @JoinColumn(name = "nhasanxuat_id")
    private NhaSanXuat nhaSanXuat;

    @OneToMany(mappedBy = "sanPham", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<ItemInvoice> itemInvoices = new ArrayList<>();
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) !=
                Hibernate.getClass(o)) return false;
        SanPham sanPham = (SanPham) o;
        return getId() != null && Objects.equals(getId(),
                sanPham.getId());
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
