package com.example.nintendogame.controller;

import com.example.nintendogame.entity.NhaSanXuat;
import com.example.nintendogame.entity.SanPham;
import com.example.nintendogame.entity.TheLoai;
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
@RequestMapping("/admin/nhasanxuats")
public class NhaSanXuatAdminController {
    @Autowired
    private NhaSanXuatService nhaSanXuatService;

    @GetMapping
    public String getAllAndPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<NhaSanXuat> nhaSanXuatPage = nhaSanXuatService.getAllAndPaged(pageable);
        model.addAttribute("nhasanxuats", nhaSanXuatPage);
        model.addAttribute("currentPage", page);
        return "admin/nhasanxuat/index";
    }

    @GetMapping("/search")
    public String searchNhaSanXuat(@RequestParam("name") String name, Model model) {
        List<NhaSanXuat> nhaSanXuats = nhaSanXuatService.searchNhaSanXuatsByName(name);
        model.addAttribute("nhasanxuats", nhaSanXuats);
        return "admin/nhasanxuat/search";
    }

    @GetMapping("/add")
    public String addNhasanxuat(Model model){
        model.addAttribute("nhasanxuat", new NhaSanXuat());
        return "admin/nhasanxuat/add";
    }
    @PostMapping("/add")
    public String addnhasanxuat( @ModelAttribute("nhasanxuat") NhaSanXuat nhaSanXuat){
        nhaSanXuatService.addnhasanxuat(nhaSanXuat);
        return "redirect:/admin/nhasanxuats";
    }
    @GetMapping("/delete/{id}")
    public String deleteNhaSanXuat(@PathVariable("id") Long Id) {
        nhaSanXuatService.deleteNhaSanXuat(Id);
        return "redirect:/admin/nhasanxuats";
    }
    @GetMapping("/edit/{id}")
    public String editNhaSanXuatForm(@PathVariable("id") Long Id, Model model) {
        NhaSanXuat nhaSanXuat = nhaSanXuatService.getNhaSanXuatById(Id);
        model.addAttribute("nhasanxuat", nhaSanXuat);
        return "admin/nhasanxuat/edit";
    }

    @PostMapping("/edit/{id}")
    public String editNhaSanXuat(@PathVariable("id") Long Id, @ModelAttribute("nhasanxuat") NhaSanXuat updatedNhaSanXuat) {
        NhaSanXuat nhaSanXuat = nhaSanXuatService.getNhaSanXuatById(Id);
        nhaSanXuat.setNsx(updatedNhaSanXuat.getNsx());
        nhaSanXuatService.updateNhaSanXuat(nhaSanXuat);
        return "redirect:/admin/nhasanxuats";
    }
}


