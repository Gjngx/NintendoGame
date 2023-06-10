package com.example.nintendogame.service;

import com.example.nintendogame.entity.SanPham;
import com.example.nintendogame.entity.User;
import com.example.nintendogame.reponsitory.IUserRepository;
import com.example.nintendogame.reponsitory.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

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