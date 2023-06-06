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
@Controller
@RequestMapping("/sanpham")
public class SanPhamController {
    @Autowired
    private SanPhamService sanPhamService;
    @GetMapping("/updatedesc")
    public String getSortedAndPagedSanPhams(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<SanPham> sanPhamsPage = sanPhamService.getSortedAndPagedSanPhams(pageable);
        model.addAttribute("sanPhams", sanPhamsPage);
        return "user/sanpham/index";
    }
    @GetMapping("/test")
    public String test(){
        return "user/sanpham/detail";
    }
}
