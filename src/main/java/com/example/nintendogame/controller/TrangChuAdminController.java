package com.example.nintendogame.controller;

import com.example.nintendogame.entity.SanPham;
import com.example.nintendogame.reponsitory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TrangChuAdminController {

    @Autowired
    private NhaSanXuatRepository nhaSanXuatRepository;
    @Autowired
    private TheLoaiRepository theLoaiRepository;
    @Autowired
    private SanPhamReponsitory sanPhamReponsitory;
    @Autowired
    private IUserRepository iUserRepository;
    @GetMapping
    public String home (Model model){
        model.addAttribute("countNhaSanXuat", nhaSanXuatRepository.count());
        model.addAttribute("countTheLoai", theLoaiRepository.count());
        model.addAttribute("countSanPham", sanPhamReponsitory.count());
        model.addAttribute("countUser", iUserRepository.count());
        return "admin/trangchu/index";
    }
}
