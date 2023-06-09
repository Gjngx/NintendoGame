package com.example.nintendogame.controller;


import com.example.nintendogame.entity.SanPham;
import com.example.nintendogame.reponsitory.TrangThaiRepository;
import com.example.nintendogame.service.NhaSanXuatService;
import com.example.nintendogame.service.SanPhamService;
import com.example.nintendogame.service.TheLoaiService;
import com.example.nintendogame.service.TrangThaiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin/sanphams")
public class SanPhamAdminController {
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private TheLoaiService theLoaiService;
    @Autowired
    private NhaSanXuatService nhaSanXuatService;
    @Autowired
    private TrangThaiService trangThaiService;
    @Autowired
    private TrangThaiRepository trangThaiRepository;
    @GetMapping
    public String getSortedAndPagedSanPhams(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<SanPham> sanPhamsPage = sanPhamService.getSortedAndPagedSanPhams(pageable);
        model.addAttribute("sanPhams", sanPhamsPage);
        model.addAttribute("currentPage", page);
        return "admin/sanpham/index";
    }
    @GetMapping("/add")
    public String addSanPhamForm(Model model){
        model.addAttribute("sanPham", new SanPham());
        model.addAttribute("theLoais", theLoaiService.GetAllTheLoaiAdmin());
        model.addAttribute("nhaSanXuats", nhaSanXuatService.GetAllNhaSanXuatAdmin());
        model.addAttribute("trangThais", trangThaiRepository.findAll());
        return "admin/sanpham/add";
    }
    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("danger", "Chưa thêm ảnh tải lên!");
            return "redirect:/admin/sanphams/add";
        }
        try {
            String filePath = "/Users/giang/Desktop/Lab_Springboot/DoAn/NintendoGame/src/main/resources/static/img/anhgame/" + file.getOriginalFilename();
            file.transferTo(new File(filePath));
            redirectAttributes.addFlashAttribute("message", "Tải ảnh thành công! Bây giờ bạn có thể thêm sản phẩm thêm mới hoặc cập nhật sản phẩm.");
            return "redirect:/admin/sanphams/add";
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("danger", "Tải ảnh thất bại!");
            return "redirect:/admin/sanphams/add";
        }
    }
    @PostMapping("/add")
    public String addSanPham(@Valid @ModelAttribute("sanPham") SanPham sanPham , BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "admin/sanpham/add";
        }
        sanPhamService.addSanPham(sanPham);
        return "redirect:/admin/sanphams";
    }
    @GetMapping("/edit/{id}")
    public String editSanPhamForm( @PathVariable("id") Long Id, Model model) {
        SanPham sanPham = sanPhamService.getSanPhamById(Id);
        model.addAttribute("sanPham", sanPham);
        model.addAttribute("theLoais", theLoaiService.GetAllTheLoaiAdmin());
        model.addAttribute("nhaSanXuats", nhaSanXuatService.GetAllNhaSanXuatAdmin());
        model.addAttribute("trangThais", trangThaiRepository.findAll());
        return "admin/sanpham/edit";
    }

    @PostMapping("/edit/{id}")
    public String editSanPham(@PathVariable("id") Long Id, @ModelAttribute("sanPham") SanPham updatedSanPham) {
        SanPham sanPham = sanPhamService.getSanPhamById(Id);
        sanPham.setSanpham(updatedSanPham.getSanpham());
        sanPham.setHinhanh(updatedSanPham.getHinhanh());
        sanPham.setTheLoai(updatedSanPham.getTheLoai());
        sanPham.setNhaSanXuat(updatedSanPham.getNhaSanXuat());
        sanPham.setTrangThai(updatedSanPham.getTrangThai());
        sanPham.setMota(updatedSanPham.getMota());
        sanPham.setSoluong(updatedSanPham.getSoluong());
        sanPham.setGia(updatedSanPham.getGia());
        sanPhamService.updateSanPham(sanPham);
        return "redirect:/admin/sanphams";
    }

    @GetMapping("/delete/{id}")
    public String deleteSanPham(@PathVariable("id") Long Id) {
        sanPhamService.deleteSanPham(Id);
        return "redirect:/admin/sanphams";
    }

}
