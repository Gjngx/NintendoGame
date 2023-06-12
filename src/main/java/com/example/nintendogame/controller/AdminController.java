package com.example.nintendogame.controller;

import com.example.nintendogame.daos.AccountDao;
import com.example.nintendogame.entity.User;
import com.example.nintendogame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/taikhoan")
public class AdminController {

    @Autowired
    private UserService userService;
    @GetMapping("/thongtin")
    public String dashboard(Model model, Authentication authentication) {
        String username = authentication.getName();
        // Sử dụng username để lấy thông tin người dùng từ cơ sở dữ liệu
        var user = userService.findByUsername(username);

        model.addAttribute("user", user);
        return "admin/taikhoan/thongtin";
    }
    @PostMapping("/doithongtin")
    public String updateUser(Model model, Authentication authentication) {
        // Lấy thông tin người dùng hiện tại
        var username = authentication.getName();
        var user = userService.findByUsername(username);
        model.addAttribute("user", user);
        // Gọi service để cập nhật thông tin người dùng
        user.setUsername(user.getUsername());
        user.setEmail(user.getEmail());
        user.setName(user.getName());
        user.setGioitinh(user.getGioitinh());
        user.setDiachi(user.getDiachi());
        userService.updateUser(user);

        // Chuyển hướng về trang thành công
        return "redirect:/success";
    }
    @GetMapping("/doimatkhau")
    public String showChangePasswordForm(Model model, Authentication authentication) {
        var username = authentication.getName();
        // Sử dụng username để lấy thông tin người dùng từ cơ sở dữ liệu
        var user = userService.findByUsername(username);

        model.addAttribute("user", user);
        return "admin/taikhoan/doimatkhau";

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
}
