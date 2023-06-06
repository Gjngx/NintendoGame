package com.example.nintendogame.service;

import com.example.nintendogame.entity.User;
import com.example.nintendogame.reponsitory.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;

    public void save(User user){
        userRepository.save(user);
    }
}