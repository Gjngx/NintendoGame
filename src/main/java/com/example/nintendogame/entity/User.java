package com.example.nintendogame.entity;

import com.example.nintendogame.validator.annotation.ValidUsername;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    @NotBlank(message = "Vui lòng nhập họ tên !")
    @Size(max = 50, message = "Tên của bạn phải nhỏ hơn 50 ký tự !")
    private String name;

    @Column(name = "email", length = 50, nullable = false)
    @Size(max = 50, message = "Email phải nhỏ hơn 50 ký tự !")
    @NotBlank(message = "Vui lòng nhập Email")
    private String email;

    @Column(name = "username", length = 50, nullable = false, unique = true)
    @NotBlank(message = "Vui lòng nhập tài khoản")
    @Size(max = 50, message = "Tài khoản của bạn phải nhỏ hơn 50 ký tự !")
    @ValidUsername
    private String username;

    @Column(name = "password", length = 250, nullable = false)
    @NotBlank(message = "Vui lòng nhập mật khẩu !")
    private String password;

    @Column(name =" diachi", length = 50)
    @NotBlank(message = "Vui lòng nhập địa chỉ !")
    private String diachi;

    @Column(name = "sdt", length = 50, nullable = false)
    @NotBlank(message = "Vui lòng nhập SDT !")
    private String sdt;

    @Column(name = "gioitinh", length = 50, nullable = false)
    @NotBlank(message = "Vui lòng chọn giới tính !")
    private String gioitinh;

    @Column(name = "ngaysinh", nullable = false)
    private String ngaysinh;

    @Column(name = "trangthai", nullable = false)
    private boolean trangthai;

    @ManyToMany(fetch=FetchType.EAGER) @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<HoaDon> hoaDons;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<GioHang> gioHangs;
}
