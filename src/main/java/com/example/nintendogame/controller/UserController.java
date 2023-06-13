package com.example.nintendogame.controller;

import com.example.nintendogame.entity.Role;
import com.example.nintendogame.entity.User;
import com.example.nintendogame.reponsitory.IUserRepository;
import com.example.nintendogame.reponsitory.RoleRepository;
import com.example.nintendogame.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping("/info")
    public String dashboard(Model model, Authentication authentication) {
        String username = authentication.getName();
        // Sử dụng username để lấy thông tin người dùng từ cơ sở dữ liệu
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        return "user/taikhoan/thongtin";
    }
    @GetMapping("/doimatkhau")
    public String showChangePasswordForm(Model model, Authentication authentication) {
        String username = authentication.getName();
        // Sử dụng username để lấy thông tin người dùng từ cơ sở dữ liệu
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        return "user/taikhoan/doimatkhau";

    }
    @PostMapping("/doimatkhau")
    public String changePassword(@RequestParam("currentPassword") String currentPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 @RequestParam("confirmPassword") String confirmPassword,
                                 Authentication authentication) {
        String username = authentication.getName();
        // Kiểm tra tính hợp lệ của mật khẩu mới
        if (!newPassword.equals(confirmPassword)) {
            // Xử lý lỗi: Mật khẩu mới không khớp
            return "redirect:/login";
        }
        // Kiểm tra tính hợp lệ của mật khẩu hiện tại
        if (!userService.isValidPassword(username, currentPassword)) {
            // Xử lý lỗi: Mật khẩu hiện tại không đúng
            return "redirect:/login";
        }
        // Thực hiện thay đổi mật khẩu
        userService.changePassword(username, newPassword);
        // Đăng xuất người dùng sau khi thay đổi mật khẩu thành công
        SecurityContextHolder.getContext().setAuthentication(null);
        return "redirect:/login";
    }
    @GetMapping("/doithongtin")
    public String thongTinTaiKhoanForm(Model model, Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        return "user/taikhoan/doithongtin";
    }
    @PostMapping("/doithongtin")
    public String thongTinTaiKhoan(Model model, Authentication authentication, User updateUser) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        model.addAttribute("user", updateUser);
        user.setEmail(updateUser.getEmail());
        user.setName(updateUser.getName());
        user.setGioitinh(updateUser.getGioitinh());
        user.setSdt(updateUser.getSdt());
        user.setDiachi(updateUser.getDiachi());
        user.setNgaysinh(updateUser.getNgaysinh());
        userService.updateUser(user);
        return "redirect:/info";
    }
}
