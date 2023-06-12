package com.example.nintendogame.service;

import com.example.nintendogame.entity.SanPham;
import com.example.nintendogame.entity.User;
import com.example.nintendogame.reponsitory.IUserRepository;
import com.example.nintendogame.reponsitory.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public boolean isValidPassword(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            // Kiểm tra tính hợp lệ của mật khẩu
            return passwordEncoder().matches(password, user.getPassword());
        }
        return false;
    }
    @Transactional
    public void updateUser(User user) {
        userRepository.save(user);
    }
    public void changePassword(String username, String newPassword) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            // Mã hóa mật khẩu mới trước khi lưu vào cơ sở dữ liệu
            user.setPassword(passwordEncoder().encode(newPassword));
            userRepository.save(user);
        }
    }
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public User getUserById(Long id){
        Optional<User> optional = userRepository.findById(id);
        return optional.orElse(null);
    }

    public Page<User> getAllAndPaged(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public void save(User user){
        userRepository.save(user);
        Long userId = userRepository.getUserIdByUsername(user.getUsername());
        Long roleId = roleRepository.getRoleIdByName("USER");
        if (roleId != 0 && userId != 0){
            userRepository.addRoleToUser(userId, roleId);
        }
    }
    public void deleteUsers(Long id){userRepository.deleteById(id);}

}