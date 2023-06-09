package com.example.nintendogame.controller;

import com.example.nintendogame.entity.User;
import com.example.nintendogame.reponsitory.IUserRepository;
import com.example.nintendogame.service.UserService;
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
@RequestMapping("/admin/users")
public class UserAdminController {
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private UserService userService;
    @GetMapping
    public String getAllAndPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = userService.getAllAndPaged(pageable);
        model.addAttribute("users", userPage);
        model.addAttribute("currentPage", page);
        return "admin/user/index";
    }
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long Id) {
        userService.deleteUsers(Id);
        return "redirect:/admin/users";
    }
}
