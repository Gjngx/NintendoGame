package com.example.nintendogame.controller;

import com.example.nintendogame.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public class NhaSanXuatController {
    @GetMapping("/{id}/sanphams")
    public String getProductsByCategory(
            @PathVariable("id") Long theLoaiId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "18") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        model.addAttribute("theLoais", theLoaiReponsitory.findAll());
        model.addAttribute("nhaSanXuats", nhaSanXuatReponsity.findAll());
        Page<SanPham> sanPhamsPage = sanPhamService.getSanPhamsByTheLoai(theLoaiId, pageable);
        model.addAttribute("Id", theLoaiId);
        model.addAttribute("sanPhams", sanPhamsPage);
        return "user/theloai/index";
    }
}
