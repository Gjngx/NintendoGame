package com.example.nintendogame.controller;

import com.example.nintendogame.entity.SanPham;
import com.example.nintendogame.entity.TheLoai;
import com.example.nintendogame.reponsitory.NhaSanXuatReponsity;
import com.example.nintendogame.reponsitory.TheLoaiReponsitory;
import com.example.nintendogame.service.NhaSanXuatService;
import com.example.nintendogame.service.SanPhamService;
import com.example.nintendogame.service.TheLoaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sanpham")
public class SanPhamController {
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private TheLoaiService theLoaiService;
    @Autowired
    private TheLoaiReponsitory theLoaiReponsitory;
    @Autowired
    private NhaSanXuatService nhaSanXuatService;
    @Autowired
    private NhaSanXuatReponsity nhaSanXuatReponsity;
    @GetMapping("/updatedesc")
    public String getSortedAndPagedSanPhams(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "18") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        model.addAttribute("theLoais", theLoaiReponsitory.findAll());
        model.addAttribute("nhaSanXuats", nhaSanXuatReponsity.findAll());
        Page<SanPham> sanPhamsPage = sanPhamService.getSortedAndPagedSanPhams(pageable);
        model.addAttribute("sanPhams", sanPhamsPage);
        model.addAttribute("currentPage", page);
        return "user/sanpham/index";
    }
    @GetMapping("/{id}")
    public String detailSanphams(@PathVariable("id") Long id, Model model){
        SanPham sanPham = sanPhamService.getSanPhamById(id);
        List<SanPham> sanPhams = sanPhamService.getLatestProducts(3);
        model.addAttribute("sanphams", sanPhams);
        model.addAttribute("sanpham", sanPham);
        return "user/sanpham/detail";
    }
    @GetMapping
    public String showAllSanpham(Model model){
        List<SanPham> sanPhams = sanPhamService.getAllSanPhams();
        model.addAttribute("sanPham", sanPhams);
        return "user/sanpham/detail";
    }
}
