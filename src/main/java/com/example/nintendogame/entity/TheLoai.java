package com.example.nintendogame.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "theLoai")
public class TheLoai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Tên thể loại không được để trống")
    @Size(max = 50, min = 1, message = "Chiều dài tên thể loại không quá 50 kí tự")
    @Column(name = "theloai")
    private String theloai;
    @OneToMany(mappedBy = "theLoai", cascade = CascadeType.ALL)
    private List<SanPham> sanPhams;
}
