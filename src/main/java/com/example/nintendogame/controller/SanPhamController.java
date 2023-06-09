package com.example.nintendogame.controller;

import com.example.nintendogame.entity.SanPham;
import com.example.nintendogame.reponsitory.NhaSanXuatRepository;
import com.example.nintendogame.reponsitory.TheLoaiRepository;
import com.example.nintendogame.service.NhaSanXuatService;
import com.example.nintendogame.service.SanPhamService;
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
    private TheLoaiRepository theLoaiRepository;
    @Autowired
    private NhaSanXuatService nhaSanXuatService;
    @Autowired
    private NhaSanXuatRepository nhaSanXuatReponsity;
    @GetMapping("/updatedesc")
    public String getSortedAndPagedSanPhams(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        model.addAttribute("theLoais", theLoaiRepository.findAll());
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
