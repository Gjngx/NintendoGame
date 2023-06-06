package com.example.nintendogame.controller;

import com.example.nintendogame.entity.NhaSanXuat;
import com.example.nintendogame.service.NhaSanXuatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
@RequestMapping("/admin/nhasanxuats")
public class NhaSanXuatController {
    @Autowired
    private NhaSanXuatService nhaSanXuatService;

    @GetMapping
    public String GetNhaSanXuat(Model model){
        List<NhaSanXuat> nhaSanXuats = nhaSanXuatService.GetAllNhaSanXuatAdmin();
        model.addAttribute("nhasanxuats", nhaSanXuats);
        return "admin/nhasanxuat/index";
    }
}
