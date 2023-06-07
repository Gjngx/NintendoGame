package com.example.nintendogame.controller;

import com.example.nintendogame.entity.NhaSanXuat;
import com.example.nintendogame.service.NhaSanXuatService;
import com.example.nintendogame.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
@RequestMapping("/admin/nhasanxuats")
public class NhaSanXuatController {
    @Autowired
    private NhaSanXuatService nhaSanXuatService;
    @Autowired
    private SanPhamService sanPhamService;

    @GetMapping
    public String GetNhaSanXuat(Model model){
        List<NhaSanXuat> nhaSanXuats = nhaSanXuatService.GetAllNhaSanXuatAdmin();
        model.addAttribute("nhasanxuats", nhaSanXuats);
        return "admin/nhasanxuat/index";
    }

    @GetMapping("/add")
    public String addNhasanxuat(Model model){
        model.addAttribute("nhasanxuat", new NhaSanXuat());
        return "admin/nhasanxuat/add";
    }
    @PostMapping("/add")
    public String addnhasanxuat( @ModelAttribute("nhasanxuat") NhaSanXuat nhaSanXuat){
        nhaSanXuatService.addnhasanxuat(nhaSanXuat);
        return "redirect:/nhasanxuats";
    }
}


