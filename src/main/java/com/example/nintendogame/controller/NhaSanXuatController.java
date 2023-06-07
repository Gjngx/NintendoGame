package com.example.nintendogame.controller;

import com.example.nintendogame.entity.SanPham;
import com.example.nintendogame.reponsitory.NhaSanXuatReponsity;
import com.example.nintendogame.reponsitory.TheLoaiReponsitory;
import com.example.nintendogame.service.SanPhamService;
import com.example.nintendogame.service.TheLoaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@RequestMapping("/nhasanxuat")
public class NhaSanXuatController {
    @Autowired
    private TheLoaiReponsitory theLoaiReponsitory;
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private NhaSanXuatReponsity nhaSanXuatReponsity;
    @GetMapping("/{id}/sanphams")
    public String getProductsByCategory(
            @PathVariable("id") Long nhaSanXuatId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "18") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        model.addAttribute("theLoais", theLoaiReponsitory.findAll());
        model.addAttribute("nhaSanXuats", nhaSanXuatReponsity.findAll());
        Page<SanPham> sanPhamsPage = sanPhamService.getSanPhamsByNhaSanXuat(nhaSanXuatId, pageable);
        model.addAttribute("Id", nhaSanXuatId);
        model.addAttribute("sanPhams", sanPhamsPage);
        return "user/nhasanxuat/index";
    }
}
