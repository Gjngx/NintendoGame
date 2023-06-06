package com.example.nintendogame.controller;

import com.example.nintendogame.entity.SanPham;
import com.example.nintendogame.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class TrangChuController {
    @Autowired
    private SanPhamService sanPhamService;
    @GetMapping
    public String home (Model model){
        List<SanPham> sanPhams = sanPhamService.getLatestProducts(12);
        model.addAttribute("sanPhams", sanPhams);
        return "user/trangchu/index";
    }
}
