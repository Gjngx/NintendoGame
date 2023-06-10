package com.example.nintendogame.controller;

import com.example.nintendogame.entity.Role;
import com.example.nintendogame.entity.SanPham;
import com.example.nintendogame.entity.User;
import com.example.nintendogame.reponsitory.IUserRepository;
import com.example.nintendogame.reponsitory.RoleRepository;
import com.example.nintendogame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/users")
public class UserAdminController {
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private RoleRepository roleRepository;
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

    @GetMapping("/edit/{id}")
    public String editUserForm( @PathVariable("id") Long Id, Model model) {
        User user = userService.getUserById(Id);
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roleRepository.findAll());
        return "admin/user/edit";
    }
    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable("id") Long Id, @RequestParam("roles") List<Long> roleIds) {
        List<User> users = iUserRepository.findAll();
        Optional<User> optionalUser = iUserRepository.findById(Id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<Role> roles = roleRepository.findAllById(roleIds);
            user.setRoles(new HashSet<>(roles));
            iUserRepository.save(user);
        }
        return "redirect:/admin/users";
    }
    @GetMapping("/{userId}/removeRole")
    public String removeRole(@PathVariable Long userId, @RequestParam("roleId") Long roleId) {
        Optional<User> optionalUser = iUserRepository.findById(userId);
        Optional<Role> optionalRole = roleRepository.findById(roleId);
        if (optionalUser.isPresent() && optionalRole.isPresent()) {
            User user = optionalUser.get();
            Role role = optionalRole.get();
            user.getRoles().remove(role);
            iUserRepository.save(user);
        }
        return "redirect:/users";
    }
}
