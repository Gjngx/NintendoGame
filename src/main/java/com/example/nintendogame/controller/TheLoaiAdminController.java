package com.example.nintendogame.controller;

import com.example.nintendogame.entity.TheLoai;
import com.example.nintendogame.service.TheLoaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/theloais")
public class TheLoaiAdminController {
    @Autowired
    private TheLoaiService theLoaiService;
    @GetMapping
    public String getAllAndPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TheLoai> theLoaiPage = theLoaiService.getAllAndPaged(pageable);
        model.addAttribute("theloais", theLoaiPage);
        model.addAttribute("currentPage", page);
        return "admin/theloai/index";
    }

    @GetMapping("/search")
    public String searchTheLoai(@RequestParam("name") String name, Model model) {
        List<TheLoai> theLoais = theLoaiService.searchTheLoaisByName(name);
        model.addAttribute("theloais", theLoais);
        return "admin/theloai/search";
    }

    @GetMapping("/add")
    public String addTheLoaiForm(Model model){
        model.addAttribute("theLoai", new TheLoai());
        return "admin/theloai/add";
    }
    @PostMapping("/add")
    public String addTheLoai( @ModelAttribute("theLoai") TheLoai theLoai){
        theLoaiService.addTheLoai(theLoai);
        return "redirect:/admin/theloais";
    }

    @GetMapping("/delete/{id}")
    public String deleteTheLoai(@PathVariable("id") Long Id) {
        theLoaiService.deleteTheLoai(Id);
        return "redirect:/admin/theloais";
    }
    @GetMapping("/edit/{id}")
    public String editTheLoaiForm(@PathVariable("id") Long Id, Model model) {
        TheLoai theLoai = theLoaiService.getTheLoaiById(Id);
        model.addAttribute("theLoai", theLoai);
        return "admin/theloai/edit";
    }

    @PostMapping("/edit/{id}")
    public String editTheLoai(@PathVariable("id") Long Id, @ModelAttribute("theLoai") TheLoai updatedTheLoai) {
        TheLoai theLoai = theLoaiService.getTheLoaiById(Id);
        theLoai.setTheloai(updatedTheLoai.getTheloai());
        theLoaiService.updateTheLoai(theLoai);
        return "redirect:/admin/theloais";
    }
}
