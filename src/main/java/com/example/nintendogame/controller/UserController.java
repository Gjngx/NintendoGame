package com.example.nintendogame.controller;

import com.example.nintendogame.entity.Role;
import com.example.nintendogame.entity.User;
import com.example.nintendogame.reponsitory.IUserRepository;
import com.example.nintendogame.reponsitory.RoleRepository;
import com.example.nintendogame.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public String login(){
        return "user/dangnhap/login";
    }
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "user/dangnhap/register";
    }
    @PostMapping("/register")
    public String register (@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors){
                model.addAttribute(error.getField() + "_error", error.getDefaultMessage());
            }
            return "user/dangnhap/register";
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userService.save(user);
        return "redirect:/login";
    }

}
